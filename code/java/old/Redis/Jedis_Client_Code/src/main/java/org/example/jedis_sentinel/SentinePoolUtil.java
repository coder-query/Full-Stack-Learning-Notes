package org.example.jedis_sentinel;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class SentinePoolUtil {
  private static Jedis jedis;
  private static JedisSentinelPool jedisSentinelPool;

  static {
    try {
      JedisPoolConfig config = new JedisPoolConfig();
      // 最大空闲连接数, 默认8个
      config.setMaxIdle(8);
      // 最大连接数, 默认8个
      config.setMaxTotal(8);
      // 最小空闲连接数, 默认0
      config.setMinIdle(0);
      // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
      config.setMaxWaitMillis(3000);
      // 在获取连接的时候检查有效性,表示取出的redis对象可用, 默认false
      config.setTestOnBorrow(true);
      // redis服务器列表
      Set<String> sentinels = new HashSet<>();
      sentinels.add("host:port1");
      sentinels.add("host:port2");
      sentinels.add("host:port3");
      // 初始化连接池
      jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels, config, "123456");
      // 从池中获取一个Jedis对象
      jedis = jedisSentinelPool.getResource();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
