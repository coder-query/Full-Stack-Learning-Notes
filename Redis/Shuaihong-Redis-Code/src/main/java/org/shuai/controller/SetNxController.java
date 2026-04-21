package org.shuai.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/setnx")
public class SetNxController {

    /**
     * 本地锁
     */
    private final static ReentrantLock localCasLock = new ReentrantLock();

    @Resource
    private JedisPool jedisPool;

    @RequestMapping("/deduct-stock")
    public String deductStock() {
        try {
            SetNxController.localCasLock.lock();
            Jedis jedis = null;
            // 从数据库里查询商品
            String lockKey = null;
            String clientId = null;
            try {
                lockKey = "lock:product_101";
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
