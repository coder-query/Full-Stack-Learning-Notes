package org.example.jedis_client;

import redis.clients.jedis.Jedis;

public class RedisStringJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    jedis.auth("123456");
    System.out.println("连接成功");
    // 设置 redis 字符串数据
    jedis.set("runoobkey", "www.runoob.com");
    // 获取存储的数据并输出
    System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
  }
}
