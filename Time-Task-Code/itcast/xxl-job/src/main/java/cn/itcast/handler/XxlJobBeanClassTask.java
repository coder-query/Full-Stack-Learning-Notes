package cn.itcast.handler;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;

public class XxlJobBeanClassTask extends IJobHandler {
    @Override
    public void execute() throws Exception {
        System.out.println("Bean模式----XxlJobBeanClassTask任务执行了--"+(System.currentTimeMillis() / 1000));
    }
}
