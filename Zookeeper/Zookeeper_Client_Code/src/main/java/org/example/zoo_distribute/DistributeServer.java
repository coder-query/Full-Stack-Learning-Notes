package org.example.zoo_distribute;

import lombok.Data;
import org.apache.zookeeper.*;

@Data
public class DistributeServer {

  private String connectonString = "192.168.211.166:2181,192.168.211.166:2182,192.168.211.166:2183";
  private ZooKeeper zkServer;
  private String parentNode = "/servers";

  public static void main(String[] args) throws InterruptedException, KeeperException {
    DistributeServer server = new DistributeServer();

    // 创建与Zookeeper集群的连接
    server.connectZookeeper();
    // 注册当前服务
    String serverHost = "服务器003";
    server.registerServer(serverHost);
    // 执行业务逻辑(睡觉)
    server.business();
  }

  private void business() {
    while (true) {
      try {
        Thread.sleep(Long.MAX_VALUE);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void registerServer(String serverHost) throws InterruptedException, KeeperException {
    String path =
        zkServer.create(
            parentNode + "/" + serverHost,
            (serverHost + "服务器数据信息---访问地址").getBytes(),
            ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.EPHEMERAL_SEQUENTIAL);
    System.out.println(serverHost + " is online...");
  }

  private void connectZookeeper() {
    try {
      zkServer =
          new ZooKeeper(
              connectonString,
              5000,
              new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {}
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
