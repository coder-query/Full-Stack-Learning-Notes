package org.shuai.controller.setnx_eee;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.servlet.http.PushBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@RestController
public class RedisDemoController {

    @Autowired
    private JedisPool jedisPool;

    @Value("${server.port}")
    private String port;

    public static final String LOCK_PREFIX = "product_stock_lock:";

    public static final String Lock_Lua =
            "if redis.call('GET', KEYS[1]) == ARGV[1] then "
            + "return redis.call('DEL', KEYS[1]) "
            + "else "
            + "return 0 "
            + "end";

    @GetMapping("/deduct-stock")
    public String deductStock(Integer productId) {

        /**
         * 在不引入redisson的情况下，如何实现分布式锁
         * UUID.fastUUID().toString(true) +
         */
        // setnx  + 过期时间
        Jedis jedis = null;
        String lockKey = LOCK_PREFIX + productId;
        String lockValue = port + UUID.fastUUID().toString(true) +Thread.currentThread().getId();
        try {
            // 拿到操作Redis的jedis
            jedis = jedisPool.getResource();
            SetParams setParams = new SetParams()
                    .nx()
                    .ex(60L);  // 估计一下
            // 设置分布式锁
            String lockResult = jedis.set(lockKey, lockValue, setParams);
            // 如果不成功，则返回失败
            if (!StrUtil.equals("OK",lockResult)){
                // 自己写自选锁 + 等待
                return "库存扣减失败";
            }
            // 获取锁成功a
            // 执行扣减库存逻辑
            try {
                this.deductStockSync();
            }finally {
                // 唤醒等待的线程
                // 释放锁 （Value相同，意味着这个是当前线程获取的锁，避免锁误删）
               jedis.eval(Lock_Lua, Collections.singletonList(lockKey), Collections.singletonList(lockValue));
            }
            return "库存扣减成功";
        }finally {
            // 释放资源，归还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void deductStockSync(){
    }
}
