package org.example.jedis_client;

import java.util.List;
import redis.clients.jedis.Jedis;

public class RedisListJava {
  public static void main(String[] args) {
    String redisHost = "192.168.211.166";
    int redisPort = 6379;
    // 连接本地的 Redis 服务
    Jedis jedis = new Jedis(redisHost, redisPort);
    // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
    jedis.auth("123456");
    System.out.println("连接成功");
    // 存储数据到列表中
    jedis.lpush("site-list", "Runoob");
    jedis.lpush("site-list", "Google");
    jedis.lpush("site-list", "Taobao");
    // 获取存储的数据并输出
    List<String> list = jedis.lrange("site-list", 0, 2);
    for (int i = 0; i < list.size(); i++) {
      System.out.println("列表项为: " + list.get(i));
    }
  }
}
