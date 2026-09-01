package org.example.lecttuce_client;

import io.lettuce.core.api.sync.RedisCommands;
import java.util.concurrent.TimeUnit;

public class Test {
  public static void main(String[] args) throws InterruptedException {
    // 1、获取RedisCommands
    RedisCommands<String, String> commands = LettuceSyncClient.getConnection();
    // 2、操作redis
    //    System.out.println("清空数据：" + commands.flushdb());
    System.out.println("判断某个键是否存在：" + commands.exists("xhz"));
    System.out.println("新增<xhz,ctr>键：" + commands.set("xhz", "ctr"));
    System.out.println("是否存在：" + commands.exists("xhz"));
    System.out.println("所有键：" + commands.keys("*"));
    System.out.println("给xhz键设置生存时间：" + commands.expire("xhz", 100L));
    // sleep1秒
    TimeUnit.SECONDS.sleep(1);
    System.out.println("查看xhz键剩余生存时间：" + commands.ttl("xhz"));
    System.out.println("查看xhz键的编码方式：" + commands.objectEncoding("xhz"));
    System.out.println("查看xhz键的类型：" + commands.type("xhz"));
    System.out.println("获取xhz键：" + commands.get("xhz"));
    System.out.println("删除xhz键：" + commands.del("xhz"));
    // 3、关闭连接
    LettuceSyncClient.close();
  }
}
