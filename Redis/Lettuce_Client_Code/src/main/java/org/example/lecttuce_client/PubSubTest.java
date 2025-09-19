package org.example.lecttuce_client;

import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

public class PubSubTest {
  public static void main(String[] args) throws InterruptedException {
    StatefulRedisPubSubConnection<String, String> pubSubConn = LettuceSyncClient.getPubSubConn();
    // 监听器对象，用于接收订阅频道的消息
    pubSubConn.addListener(
        new RedisPubSubListener<String, String>() {
          @Override
          public void message(String channel, String message) {
            System.out.println(
                "Received new message - Channel: " + channel + ", Message: " + message);
          }

          @Override
          public void message(String pattern, String channel, String message) {
            System.out.println(
                "pattern = " + pattern + "，channel = " + channel + "，message = " + message);
          }

          @Override
          public void subscribed(String channel, long count) {
            System.out.println("channel = " + channel);
          }

          @Override
          public void psubscribed(String pattern, long count) {
            System.out.println("pattern = " + pattern);
          }

          @Override
          public void unsubscribed(String channel, long count) {
            System.out.println("channel = " + channel);
          }

          @Override
          public void punsubscribed(String pattern, long count) {
            System.out.println("pattern = " + pattern);
          }
        });
    // 订阅聊天频道
    pubSubConn.sync().subscribe("chat");
    // 模拟用户发送消息
    String user = "User1";
    String message = "Hello, world!";
    // 将消息发布到聊天频道
    RedisCommands<String, String> connection = LettuceSyncClient.getConnection();
    Long messagesSent = connection.publish("chat", "[" + user + "]: " + message);
    System.out.println("Messages sent: " + messagesSent);
    // 关闭连接
    LettuceSyncClient.close();
  }
}
