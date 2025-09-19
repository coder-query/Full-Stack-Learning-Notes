package org.example.jedis_pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;

public class JedisPoolFactory {
  private static redis.clients.jedis.JedisPool jedisPool;
  private static final String REDIS_HOST = "192.168.211.166";
  private static final int REDIS_PORT = 6379;
  private static final String REDIS_PASSWORD = "123456";

  static {
    // 1. 创建连接池配置对象
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMaxTotal(100); // 最大连接数（空闲 + 正在使用）
    poolConfig.setMaxIdle(30); // 最大空闲连接数
    poolConfig.setMinIdle(5); // 最小空闲连接数（保持长连接，避免频繁创建）
    poolConfig.setMaxWaitMillis(3000); // 申请连接时的最大等待时间（超时抛异常）
    poolConfig.setTestOnBorrow(true); // 借连接时测试有效性（保证拿到的连接可用）
    poolConfig.setTestOnReturn(true); // 还连接时测试有效性
    poolConfig.setTestWhileIdle(true); // 后台线程检测空闲连接有效性
    poolConfig.setTimeBetweenEvictionRunsMillis(30000); // 后台检测线程的执行周期（毫秒）

    // 2. 创建 JedisPool（指定 Redis 地址、端口、密码）
    jedisPool =
        new redis.clients.jedis.JedisPool(poolConfig, REDIS_HOST, REDIS_PORT, 6000, REDIS_PASSWORD);
  }

  // 3. 提供获取连接的方法
  public static Jedis getResource() {
    return jedisPool.getResource();
  }

  // 4. 关闭连接池（项目关闭时调用）
  public static void closePool() {
    if (jedisPool != null && !jedisPool.isClosed()) {
      jedisPool.close();
    }
  }
}
