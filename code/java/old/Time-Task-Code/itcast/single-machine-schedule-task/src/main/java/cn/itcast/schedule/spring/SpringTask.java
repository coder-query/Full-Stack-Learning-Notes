package cn.itcast.schedule.spring;

import cn.itcast.util.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Scheduled(cron = "0/5 * * * * ?")
    //@Async
    public void testTask() throws InterruptedException {


        System.out.println("执行SpringTask任务，时间:" + LocalDateUtils.getLocalDateTimeStr());
    }

}
