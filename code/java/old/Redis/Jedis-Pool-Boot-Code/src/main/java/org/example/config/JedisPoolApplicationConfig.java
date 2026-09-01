package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class JedisPoolApplicationConfig {

    @Value("${spring.redis.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;

    @Value("${spring.redis.jedis.pool.max-idle:8}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait-millis:-1}")
    private int maxWaitMillis;

    @Value("${spring.redis.jedis.pool.max-active:8}")
    private Integer maxActive;

    @Value("${spring.redis.jedis.pool.min-idle:0}")
    private Integer minIdle;

    @Value("${spring.redis.timeout:3000}")
    private Integer timeout;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setBlockWhenExhausted(false);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, timeout, password);
    }
}
