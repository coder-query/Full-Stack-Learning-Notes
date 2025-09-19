package org.example.redis_client;

import redis.clients.jedis.Jedis;

public class RedisZSetJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
    jedis.auth("123456");
    System.out.println("连接成功");

    jedis.zadd("myZSet", 1, "张三");
    jedis.zadd("myZSet", 2, "李四");
    jedis.zadd("myZSet", 3, "王五");
    jedis.zadd("myZSet", 4, "小姐");
    jedis.zadd("myZSet", 5, "小哥");
    System.out.println(jedis.zrange("myZSet", 0, -1));
    System.out.println(jedis.zrangeByScore("myZSet", 1, 4));
  }
}
