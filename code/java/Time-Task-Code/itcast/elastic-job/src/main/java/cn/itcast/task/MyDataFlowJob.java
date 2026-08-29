package cn.itcast.task;

import cn.itcast.entity.Order;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyDataFlowJob implements DataflowJob<Order> {
    Logger log = LoggerFactory.getLogger(MyDataFlowJob.class);

    //模拟100个未处理订单
    private static List<Order> orders = new ArrayList<>();

    {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setStatus(0);
            orders.add(order);
        }
    }


    @Override
    public List<Order> fetchData(ShardingContext shardingContext) {
        //订单号%分片总数==当前分片项
        List<Order> orderList = orders.stream().filter(o -> o.getStatus() == 0)
                .filter(o -> o.getOrderId() % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem())
                .collect(Collectors.toList());

        List<Order> subList = null;
        if (orderList != null && orderList.size() > 0) {
            int endIndex = 10;
            if (orderList.size() < 10) {
                endIndex = orderList.size() - 1;
            }
            subList = orderList.subList(0, endIndex);
        }
        //由于抓取数据过快，为更好看出效果，此处休眠一会儿
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("分片项:{},我抓取的数据:{}", shardingContext.getShardingItem(), subList);
        return subList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Order> list) {
        list.forEach(o -> o.setStatus(1));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("分片项:{},处理中.....", shardingContext.getShardingItem());
    }

}
