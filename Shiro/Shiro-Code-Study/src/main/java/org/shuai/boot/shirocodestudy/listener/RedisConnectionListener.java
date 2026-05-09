package org.shuai.boot.shirocodestudy.listener;

import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Component
public class RedisConnectionListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionListener.class);

    @Resource
    private JedisPool jedisPool;

    @Value("${redis.host:127.0.0.1}")
    private String host;

    @Value("${redis.port:6379}")
    private int port;

    @Value("${redis.database:0}")
    private int database;

    @Value("${redis.pool.max-idle:8}")
    private int maxIdle;

    @Value("${redis.pool.min-idle:0}")
    private int minIdle;

    @Override
    public void onApplicationEvent(@NonNullDecl ApplicationReadyEvent event) {
        // redis 连接池的预热
        preWarmRedisPool();
        // 打印 redis 连接信息
        testRedisConnection();
    }
    private void preWarmRedisPool() {
        List<Jedis> minIdleJedisList = new ArrayList<Jedis>(minIdle);
        AtomicInteger successCount = new AtomicInteger(0);
        for (int i = 0; i < minIdle; i++) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                minIdleJedisList.add(jedis);
                jedis.ping();
                logger.info("✅ 开始redis 连接池预热！successCount:{}" , successCount.incrementAndGet());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                //注意，这里不能马上close将连接还回连接池，否则最后连接池里只会建立1个连接。。
                //jedis.close();
            }
        }
         //统一将预热的连接还回连接池
        for (int i = 0; i < minIdle; i++) {
            Jedis jedis = null;
            try {
                jedis = minIdleJedisList.get(i);
                //将连接归还回连接池
                jedis.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
            }
        }
    }

    private void testRedisConnection() {
        try (Jedis jedis = jedisPool.getResource()) {
            String pingResult = jedis.ping();
            if ("PONG".equals(pingResult)) {
                logger.info("✅ Redis连接成功！");
            } else {
                logger.error("❌ Redis连接异常：PING命令返回非预期结果：{}", pingResult);
            }
            // 打印连接信息
            logger.info("Redis配置信息 - Host: {}, Port: {}, Database: {}", host, port, database);
        } catch (Exception e) {
            logger.error("❌ Redis连接失败！原因：{}", e.getMessage(), e);
        }
    }
}
