package org.example.redis_client;

import redis.clients.jedis.Jedis;

public class RedisJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
    jedis.auth("123456");
    System.out.println("连接成功");
    // 查看服务是否运行
    System.out.println("服务正在运行: " + jedis.ping());
  }
}
