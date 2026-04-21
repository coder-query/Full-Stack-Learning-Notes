package org.shuai.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientAutoConfiguration {

    @Value("${redis.host:127.0.0.1}")
    private String host;

    @Value("${redis.port:6379}")
    private int port;

    @Value("${redis.password:}")
    private String password;

    @Value("${redis.database:0}")
    private int database;

    @Value("${redis.redisson.connection-minimum-idle-size:10}")
    private int connectionMinimumIdleSize;

    @Value("${redis.redisson.connection-pool-size:64}")
    private int connectionPoolSize;

    @Value("${redis.redisson.idle-connection-timeout:10000}")
    private int idleConnectionTimeout;

    @Value("${redis.redisson.connect-timeout:10000}")
    private int connectTimeout;

    @Value("${redis.redisson.timeout:3000}")
    private int timeout;

    @Value("${redis.redisson.retry-attempts:3}")
    private int retryAttempts;

    @Value("${redis.redisson.retry-interval:1500}")
    private int retryInterval;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password.isEmpty() ? null : password)
                .setDatabase(database)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectTimeout)
                .setTimeout(timeout)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval);
        return Redisson.create(config);
    }
}
