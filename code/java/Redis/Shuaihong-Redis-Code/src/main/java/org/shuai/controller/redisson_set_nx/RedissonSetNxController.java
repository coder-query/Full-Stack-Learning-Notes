
package org.shuai.controller.redisson_set_nx;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.shuai.entity.Stock;
import org.shuai.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/redisson-setnx")
@Api(tags = "redisson setnx命令")
@Slf4j
public class RedissonSetNxController {

    @Resource
    private StockMapper stockMapper;

    @Value("${server.port}")
    private String port;

    @Resource
    private RedissonClient redissonClient;

    public static final String SET_NX_LOCK_PREFIX = "redisson_set_nx_lock:";

    public static final String SET_NX_LOCK_LUA_SCRIPT =
                                "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                "return redis.call('del', KEYS[1])" +
                                "else return 0 " +
                                "end";

    @GetMapping(value = "/deduct-stock")
    @ApiOperation(value = "扣减库存")
    public String deductStock(Integer productId) {

        // 获取锁的key ( 保证每次操作商品为productId时的锁是唯一的 )
        String lockKey = SET_NX_LOCK_PREFIX + productId;
        // lockValue 这里不需要手动设置？？？？？
        RLock setNxLock = redissonClient.getLock(lockKey);

        try {
            // 不传入时间，默认-1，则开启看门狗机制（锁续期机制）
            setNxLock.lock();
//            ReentrantLock localCasLock = new ReentrantLock(false);
//            localCasLock.lock();
            Map<String, Object> resultMap = deductStockSync(productId);
            return (String) (resultMap.get("msg"));
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
            return "error_code: 获取分布式锁失败，请稍后重试";
        }finally {
            // 释放锁
            setNxLock.unlock();
        }
    }

    public Map<String,Object> deductStockSync(Integer productId){

        // 查询库存
        Stock stock = stockMapper.selectOne(Wrappers.<Stock>lambdaQuery().eq(Stock::getId, productId));
        if (Objects.isNull(stock) || stock.getProductCount() <= 0){
            log.error("库存不足 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            return Collections.singletonMap("msg", "库存不足");
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
            return Collections.singletonMap("msg", "库存扣减失败，请重试");
        }

        log.info("库存扣减成功 for product ID: {} ，线程名称：{}，线程ID：{}, 原库存：{}，扣减后库存剩余：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId(), oldStock, newStock);
        return Collections.singletonMap("msg", "库存扣减成功，原库存：" + oldStock + "，扣减后库存剩余：" + newStock);
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
