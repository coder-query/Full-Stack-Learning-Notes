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
//        // 设置锁的watchdog超时时间
        config.setLockWatchdogTimeout(9);  // 自定义手动配置
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password.isEmpty() ? null : password)
                .setDatabase(database)
                // 设置最小空闲连接数
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                // 设置连接池大小
                .setConnectionPoolSize(connectionPoolSize)
                // 设置空闲连接超时时间
                .setIdleConnectionTimeout(idleConnectionTimeout)
                // 设置连接超时时间
                .setConnectTimeout(connectTimeout)
                // 设置超时时间
                .setTimeout(timeout)
                // 设置重试次数
                .setRetryAttempts(retryAttempts)
                // 设置重试间隔
                .setRetryInterval(retryInterval);
        return Redisson.create(config);
    }
}
