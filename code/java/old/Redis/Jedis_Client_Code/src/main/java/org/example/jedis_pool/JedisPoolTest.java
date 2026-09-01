package org.example.jedis_pool;

import redis.clients.jedis.Jedis;

public class JedisPoolTest {
  public static void main(String[] args) {
    Jedis jedis = null;
    try {
      // 1. 获取连接
      jedis = JedisPoolFactory.getResource();
      // 2. 操作 Redis
      jedis.set("key666", "value666");
      String result = jedis.get("key666");
      System.out.println("Result: " + result);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 3. 释放连接（关键！否则连接池会被撑满）
      if (jedis != null) {
        jedis.close(); // 底层会将连接归还池化，不是真正关闭
      }
    }
    // 项目关闭前，关闭连接池（可选，一般容器会自动处理）
    JedisPoolFactory.closePool();
  }
}
