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

    @Override
    public void onApplicationEvent(@NonNullDecl ApplicationReadyEvent event) {
        testRedisConnection();
    }

    private void testRedisConnection() {
        // 查询 mysql
        // 调用jedis缓存预热
        // redisson 布隆过滤器预热
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
