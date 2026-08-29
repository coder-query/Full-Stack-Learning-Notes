package cn.itcast.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class XxlJobBeanMethodTask {

    private static final Logger logger = LoggerFactory.getLogger(XxlJobBeanMethodTask.class);



    @PostConstruct
    public void regisJob() {
        XxlJobExecutor.registJobHandler("beanModeJob",new XxlJobBeanClassTask());
    }





    @XxlJob("methodModelTask")
    public void methodModelTask() throws InterruptedException {
        logger.info("methodModelTask 定时任务启动,总分片:{},当前分片:{},参数:{}", XxlJobHelper.getShardTotal(), XxlJobHelper.getShardIndex(), XxlJobHelper.getJobParam());
        XxlJobHelper.log("XXL-METHODTASK, methodTask定时任务启动");
        //休眠10S模拟服务执行超时
        //Thread.sleep(10000);
        //执行成功标志，默认就是执行成功
        XxlJobHelper.handleSuccess();
    }
}
