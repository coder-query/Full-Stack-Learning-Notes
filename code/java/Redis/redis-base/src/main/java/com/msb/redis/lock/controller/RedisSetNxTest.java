package com.msb.redis.lock.controller;


import cn.hutool.core.util.StrUtil;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/redis-setnx")
public class RedisSetNxTest {

    private final static ReentrantLock localCasLock = new ReentrantLock();

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/deduct_stock")
    public String deductStock() {
        try {
            localCasLock.lock();
            Jedis jedis = null;
            String lockKey = "lock:product_101";
            String clientId = UUID.randomUUID() + Thread.currentThread().getName() + System.currentTimeMillis();
            try {
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
                int stock = Integer.parseInt(jedis.get("stock"));
                if (stock > 0) {
                    int realStock = stock - 1;
                    jedis.set("stock", realStock + "");
                    System.out.println("扣减成功，剩余库存:" + realStock);
                } else {
                    System.out.println("扣减失败，库存不足");
                }
            }finally {
                if (jedis != null) {
                    if (jedis.get(lockKey).equals(clientId)){
                        jedis.del(lockKey);
                    }
                    jedis.close();
                }
            }
            return "扣减库存成功！！！";
        }finally {
            localCasLock.unlock();
        }
    }
}
