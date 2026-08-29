package org.example.lecttuce_client;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LettuceSyncClient {
  private static final String HOST = "192.168.211.166";
  private static final int PORT = 6379;
  private static final String PASSWORD = "123456";

  private static RedisClient redisClient;
  private static StatefulRedisConnection<String, String> connection;
  private static RedisCommands<String, String> syncCommands;
  // 响应式编程
  private static RedisReactiveCommands<String, String> reactiveCommands;
  // 发布订阅
  private static StatefulRedisPubSubConnection<String, String> pubSubConn;

  public static RedisCommands<String, String> getConnection() {
    if (syncCommands == null) {
      getConn();
      syncCommands = connection.sync();
    }
    return syncCommands;
  }

  /**
   * 响应式编程
   *
   * @return
   */
  public static RedisReactiveCommands<String, String> getReactiveConn() {
    if (reactiveCommands == null) {
      getConn();
      // 响应式编程
      reactiveCommands = connection.reactive();
    }
    return reactiveCommands;
  }

  /**
   * 发布订阅
   *
   * @return
   */
  public static StatefulRedisPubSubConnection<String, String> getPubSubConn() {
    if (pubSubConn == null) {
      getConn();
      // 发布订阅
      pubSubConn = redisClient.connectPubSub();
    }
    return pubSubConn;
  }

  public static void getConn() {
    RedisURI redisUri =
        RedisURI.builder()
            .withHost(HOST)
            .withPort(PORT)
            .withPassword(PASSWORD)
            .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
            .build();
    redisClient = RedisClient.create(redisUri);
    connection = redisClient.connect();
  }

  public static void close() {
    if (connection != null && syncCommands != null) {
      connection.close();
      redisClient.shutdown();
    }
  }
}
