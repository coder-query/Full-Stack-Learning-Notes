package org.shuai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisPoolAutoConfiguration {

    @Value("${redis.host:127.0.0.1}")
    private String host;

    @Value("${redis.port:6379}")
    private int port;

    @Value("${redis.password:}")
    private String password;

    @Value("${redis.database:0}")
    private int database;

    @Value("${redis.timeout:2000}")
    private int timeout;

    @Value("${redis.pool.max-total:8}")
    private int maxTotal;

    @Value("${redis.pool.max-idle:8}")
    private int maxIdle;

    @Value("${redis.pool.min-idle:0}")
    private int minIdle;

    @Value("${redis.pool.max-wait-millis:-1}")
    private long maxWaitMillis;

    @Value("${redis.pool.test-on-borrow:false}")
    private boolean testOnBorrow;

    /**
     * jedis pool config
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        return config;
    }

    /**
     * jedis pool
     */
    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }
}
