package org.example.jedis_client;

import redis.clients.jedis.Jedis;

public class RedisSetJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
    jedis.auth("123456");
    System.out.println("连接成功");

    jedis.sadd("mySet", "1");
    jedis.sadd("mySet", "2");
    jedis.sadd("mySet", "3");
    System.out.println(jedis.smembers("mySet"));
    System.out.println(jedis.sismember("mySet", "1"));
  }
}
