package org.example.zoo_distribute;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.apache.zookeeper.*;

@Data
public class DistributeClient {

  private String connectonString = "192.168.211.166:2181,192.168.211.166:2182,192.168.211.166:2183";
  private ZooKeeper zkServer;
  private String parentNode = "/servers";

  public static void main(String[] args) throws InterruptedException, KeeperException {
    DistributeClient server = new DistributeClient();

    // 创建与Zookeeper集群的连接
    server.connectZookeeper();
    // 监听服务上线情况
    server.watcherServerOnline();
    // 执行业务逻辑(睡觉)
    server.business();
  }

  private void watcherServerOnline() throws InterruptedException, KeeperException {
    List<String> children = zkServer.getChildren(parentNode, true);
    System.out.println("----------------正在监听服务上线情况---------------");
    ArrayList<String> serverLsit = new ArrayList<>();
    for (String path : children) {
      byte[] data = zkServer.getData(parentNode + "/" + path, false, null);
      String serverData = new String(data);
      serverLsit.add(serverData);
    }
    System.out.println("----------------服务列表：" + serverLsit + "---------------");
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

  private void connectZookeeper() {
    try {
      zkServer =
          new ZooKeeper(
              connectonString,
              5000,
              new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                  try {
                    watcherServerOnline();
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  } catch (KeeperException e) {
                    throw new RuntimeException(e);
                  }
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
