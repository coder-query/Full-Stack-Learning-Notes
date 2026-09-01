package cn.itcast.task;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MySimpleJob implements SimpleJob {
    Logger logger = LoggerFactory.getLogger(MySimpleJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info(String.format("Thread Name: %s, 作业分片总数: %s, "
                        + "当前分片项: %s.当前参数: %s,"
                        + "作业名称: %s.作业自定义参数: %s",
                Thread.currentThread().getName(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()));
    }
}
