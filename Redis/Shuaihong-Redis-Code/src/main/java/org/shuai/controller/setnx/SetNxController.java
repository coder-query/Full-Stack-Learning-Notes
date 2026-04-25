package org.shuai.controller.setnx;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.shuai.entity.Stock;
import org.shuai.mapper.StockMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/setnx")
@Api(tags = "setnx命令")
@Slf4j
public class SetNxController {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private StockMapper stockMapper;

    public static final String SET_NX_LOCK_PREFIX = "redis_set_nx_lock:";

    public static final String SET_NX_LOCK_LUA_SCRIPT =
                                "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                "return redis.call('del', KEYS[1])" +
                                " else return 0 " +
                                "end";

    @GetMapping(value = "/deduct-stock")
    @ApiOperation("扣减库存")
    public String deductStock(Integer productId) {
        Jedis jedis = null;
        try {
            /**
             *  1. 获取Jedis连接，jedisPool中已经线程安全
             */
            jedis = jedisPool.getResource();

            /**
             * 2. 获取分布式锁
             */
            // 2.1 获取锁的key ( 保证每次操作商品为productId时的锁是唯一的 )
            String lockKey = SET_NX_LOCK_PREFIX + productId;
            // 2.2 获取锁的value ( 打标记，当前xxxjar包应用的线程获取了分布式锁 )
            String clientId = UUID.fastUUID().toString(true) + Thread.currentThread().getName() + System.currentTimeMillis();

            // 2.3 设置锁的过期时间 ( 防止死锁 )
            SetParams setParams = new SetParams()
                    .ex(10L)
                    .nx();
            String result = jedis.set(lockKey, clientId, setParams);

//            // 错误的setnx示例 （非原子操作）
//            String set = jedis.set("set_nx_lock:" + productId, clientId);
//            jedis.expire("set_nx_lock:" + productId, 60L);
//

            // 2.4 验证获取锁是否成功
            if (!StrUtil.equals(result, "OK")) {
                log.error("获取分布式锁失败，当前分布式锁被占用中。。。lockKey: {}", lockKey);
                return "error_code: 当前分布式锁被占用，请稍后重试";
            }

            /**
             * 3. 获取分布式锁成功后，执行业务逻辑
              */
            try {
                // 3.1 扣减库存
                Map<String, Object> resultMap = deductStockSync(productId);
                return (String) (resultMap.get("msg"));
            } finally {

//                // 4. 释放分布式锁 （这个直接删除锁，会因为线程执行业务时间不同，导致锁误删）
//                jedis.del(lockKey);

//                // 4. 释放分布式锁 （错误的释放锁方式，非原子操作，会导致锁误删）
//                if (StrUtil.equals(clientId, jedis.get(lockKey))) {
//                    jedis.del(lockKey);
//                }
                Integer evalResult = (Integer) jedis.eval(SET_NX_LOCK_LUA_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(clientId));
                if (evalResult == 0) {
                    log.error("释放分布式锁失败，请检查lua脚本,或检查redis服务器");
                }
                log.info("释放分布式锁成功");
            }
        } catch (Exception e) {
            log.error("扣减库存异常", e);
            return "系统错误";
        } finally {
            // 5. 归还连接
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public Map<String,Object> deductStockSync(Integer productId){

        // 查询库存
        Stock stock = stockMapper.selectOne(Wrappers.<Stock>lambdaQuery().eq(Stock::getId, productId));
        if (Objects.isNull(stock) || stock.getProductCount() <= 0){
            log.error("库存不足 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            return Map.of("msg", "库存不足");
        }

        // 原库存
        int oldStock = stock.getProductCount();
        // 扣减后库存
        int newStock = oldStock - 1;

        // 扣减库存
        // 带乐观锁的更新
        int updateFlag = stockMapper.update(null,
                Wrappers.<Stock>lambdaUpdate()
                        .set(Stock::getProductCount, newStock)
                        .eq(Stock::getId, productId)
                        .eq(Stock::getProductCount, oldStock)// 关键：乐观锁条件
        );

        if (updateFlag <= 0){
            log.error("库存扣减失败 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            return Map.of("msg", "库存扣减失败，请重试");
        }

        log.info("库存扣减成功 for product ID: {} ，线程名称：{}，线程ID：{}, 原库存：{}，扣减后库存剩余：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId(), oldStock, newStock);
        return Map.of("msg", "库存扣减成功，原库存：" + oldStock + "，扣减后库存剩余：" + newStock);
    }


    /**
     * 获取数据库中所有的商品信息
     */
    @GetMapping("/get-all-stock")
    @ApiOperation(value = "获取数据库中所有的商品信息")
    public List<Stock> getAllStock() {
        return stockMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 恢复所有商品数量为100
     */
    @GetMapping("/restore-stock")
    @ApiOperation(value = "恢复所有商品数量为100")
    public String restoreStock() {
        stockMapper.update(null, Wrappers.<Stock>lambdaUpdate()
                .set(Stock::getProductCount, 100)
                .ne(Stock::getProductCount, -66666));
        return "库存恢复成功";
    }
}
