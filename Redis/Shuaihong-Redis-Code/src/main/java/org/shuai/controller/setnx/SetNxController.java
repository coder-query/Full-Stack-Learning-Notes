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

    @GetMapping(value = "/deduct-stock")
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
