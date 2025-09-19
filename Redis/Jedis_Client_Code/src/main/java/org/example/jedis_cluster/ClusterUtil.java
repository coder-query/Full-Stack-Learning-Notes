package org.example.jedis_cluster;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class ClusterUtil {
  private static JedisCluster jedisCluster;

  static {
    try {
      Set<HostAndPort> nodes = new HashSet<>();
      nodes.add(new HostAndPort("host", 2222));
      nodes.add(new HostAndPort("host", 3333));
      nodes.add(new HostAndPort("host", 4444));
      nodes.add(new HostAndPort("host", 5555));
      nodes.add(new HostAndPort("host", 6666));
      nodes.add(new HostAndPort("host", 7777));
      jedisCluster = new JedisCluster(nodes);
      jedisCluster.set("key", "hello world");
      jedisCluster.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
