package org.example.lecttuce_client;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;

public class MsTest {

  public static void main(String[] args) {
    // 这里只需要配置一个节点的连接信息，不一定需要是主节点的信息，从节点也可以;可以自动发现主从节点
    RedisURI uri =
        RedisURI.builder()
            .withHost("192.168.211.166")
            .withPort(6379)
            .withPassword("123456")
            .build();
    RedisClient client = RedisClient.create(uri);
    StatefulRedisMasterReplicaConnection<String, String> connection =
        MasterReplica.connect(client, StringCodec.UTF8, uri);
    // 从节点读取数据
    connection.setReadFrom(ReadFrom.REPLICA);
    RedisCommands<String, String> commands = connection.sync();
    commands.set("name", "xhz");
    System.out.println(commands.get("name"));
    connection.close();
    client.shutdown();
  }
}
