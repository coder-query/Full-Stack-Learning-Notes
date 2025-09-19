package org.example.redis_client;

import redis.clients.jedis.Jedis;

public class RedisHashJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
    jedis.auth("123456");
    System.out.println("连接成功");

    jedis.hset("user", "name", "zhangsan");
    jedis.hset("user", "age", "18");
    jedis.hset("user", "sex", "man");
    System.out.println(jedis.hget("user", "name"));
    System.out.println(jedis.hgetAll("user"));
  }
}
