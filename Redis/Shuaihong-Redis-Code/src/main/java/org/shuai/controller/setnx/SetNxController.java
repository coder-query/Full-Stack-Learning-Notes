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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/setnx")
@Api(tags = "setnx命令")
@Slf4j
public class SetNxController {

    /**
     * 本地锁
     */
    private final static ReentrantLock localCasLock = new ReentrantLock();

    @Resource
    private JedisPool jedisPool;

    @Resource
    private StockMapper stockMapper;

    @GetMapping("/deduct-stock")
    @ApiOperation("扣减库存")
    public String deductStock(Integer productId) {
        try {
            SetNxController.localCasLock.lock();
            Jedis jedis = null;
            // 从数据库里查询商品
            String lockKey = null;
            String clientId = null;
            try {
                lockKey = "lock_" + productId;
                clientId = UUID.fastUUID().toString(true) + Thread.currentThread().getName() + System.currentTimeMillis();
                jedis = jedisPool.getResource();
                long expireTime = 60;
                SetParams setParams = new SetParams();
                setParams.ex(expireTime);
                setParams.nx();
                // 设置分布式锁
                String result = jedis.set(lockKey, clientId, setParams);
                System.out.println("result:" + result);
                if (!StrUtil.equals(result, "OK")) {
                    return "error_code 当前 set ex nx  返回null，表示分布式锁被占用。。。";
                }

                // 获取锁成功
                Stock stock = stockMapper.selectOne(Wrappers.<Stock>lambdaQuery().eq(Stock::getId, productId));
                Integer productCount = stock.getProductCount();
                if (productCount > 0) {
                    int realStock = productCount - 1;
                    stock.setProductCount(realStock);
                    stockMapper.update(null, Wrappers.<Stock>lambdaUpdate().set(Stock::getProductCount, realStock).eq(Stock::getId, productId));
                    System.out.println("扣减成功，剩余库存:" + realStock);
                } else {
                    System.out.println("扣减失败，库存不足");
                }
            } finally {
                if (jedis != null) {
                    if (jedis.get(lockKey).equals(clientId)) {
                        jedis.del(lockKey);
                    }
                    jedis.close();
                }
            }
            return "扣减库存成功！！！";
        } finally {
            localCasLock.unlock();
        }
    }

    @GetMapping("/get-all-stock")
    @ApiOperation("获取所有库存")
    public List<Stock> getAllStock() {
        try {
            return stockMapper.selectList(Wrappers.<Stock>lambdaQuery());
        } catch (Exception e) {
            log.error("获取所有库存失败", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/restore-stock")
    @ApiOperation("恢复库存为100")
    public String restoreStock() {
        try {
            stockMapper.update(null, Wrappers.<Stock>lambdaUpdate().set(Stock::getProductCount, 100).eq(Stock::getId, 1));
        } catch (Exception e) {
            log.error("恢复库存失败", e);
            return "error_code";
        }
        return "恢复库存成功！！！";
    }
}
