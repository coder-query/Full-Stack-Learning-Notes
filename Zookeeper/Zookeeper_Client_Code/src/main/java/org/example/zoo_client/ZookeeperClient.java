package org.example.zoo_client;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperClient {

    private ZooKeeper zooKeeper = null;

  @Before
  public void init() throws IOException {
      int sessionTimeout = 5000;
      String connectionString = "192.168.211.101:2181,192.168.211.102:2181,192.168.211.103:2181";
      zooKeeper = new ZooKeeper(connectionString, sessionTimeout, watchedEvent -> {
        System.out.println("监听事件：" + watchedEvent);
      });
  }

  // 创建节点
  @Test
  public void createNode() throws Exception {
    String path = zooKeeper.create("/data-zsh", "shuaihong-coding".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    System.out.println("创建节点成功，节点的路径为：" + path);
  }

  // 获取节点数据
  @Test
  public void getNodeData() throws Exception {
    byte[] data = zooKeeper.getData("/data-zsh", false, null);
    System.out.println("节点数据为：" + new String(data));
  }

  // 获取节点下的数据
    @Test
    public void getChildrenData() throws Exception {
        List<String> children = zooKeeper.getChildren("/data-zsh", false);
        System.out.println("节点下的数据为：" + children);
    }
}
