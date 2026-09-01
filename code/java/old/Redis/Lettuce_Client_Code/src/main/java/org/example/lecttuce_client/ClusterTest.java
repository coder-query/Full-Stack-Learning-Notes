package org.example.lecttuce_client;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.Executions;
import io.lettuce.core.cluster.api.sync.NodeSelection;
import io.lettuce.core.cluster.api.sync.NodeSelectionCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClusterTest {
  public static void main(String[] args) {
    Set<RedisURI> uris = new HashSet<>();
    uris.add(RedisURI.builder().withHost("host").withPort(1111).withPassword("123456").build());
    uris.add(RedisURI.builder().withHost("host").withPort(2222).withPassword("123456").build());
    uris.add(RedisURI.builder().withHost("host").withPort(3333).withPassword("123456").build());
    uris.add(RedisURI.builder().withHost("host").withPort(4444).withPassword("123456").build());
    uris.add(RedisURI.builder().withHost("host").withPort(5555).withPassword("123456").build());
    uris.add(RedisURI.builder().withHost("host").withPort(6666).withPassword("123456").build());

    RedisClusterClient client = RedisClusterClient.create(uris);
    StatefulRedisClusterConnection<String, String> connection = client.connect();
    RedisAdvancedClusterCommands<String, String> commands = connection.sync();
    commands.set("name", "xhz");
    System.out.println(commands.get("name"));

    // 选择从节点,只读
    NodeSelection<String, String> replicas = commands.replicas();
    NodeSelectionCommands<String, String> nodeSelectionCommands = replicas.commands();
    Executions<List<String>> keys = nodeSelectionCommands.keys("*");
    keys.forEach(key -> System.out.println(key));

    connection.close();
    client.shutdown();
  }
}
