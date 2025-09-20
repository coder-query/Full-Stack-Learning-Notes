package org.example.zoo_client;

import java.io.IOException;
import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperClient {

  private static String connectionString =
      "192.168.211.166:2181,192.168.211.166:2182,192.168.211.166:2183";
  private static int sessionTimeout = 5000;
  private ZooKeeper zooKeeper = null;

  @Before
  public void init() throws IOException {
    zooKeeper =
        new ZooKeeper(
            connectionString,
            sessionTimeout,
            new Watcher() {
              @Override
              public void process(WatchedEvent watchedEvent) {}
            });
  }

  // 创建节点
  @Test
  public void createNode() throws Exception {
    String path =
        zooKeeper.create(
            "/data-zsh",
            "shuaihong-coding".getBytes(),
            ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.PERSISTENT);
    System.out.println("创建节点成功，节点的路径为：" + path);
  }

  // 获取节点数据
  @Test
  public void getNodeData() throws Exception {
    byte[] data = zooKeeper.getData("/data-zsh", false, null);
    System.out.println("节点数据为：" + new String(data));
  }
}
