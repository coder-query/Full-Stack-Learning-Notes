package cn.itcast.schedule.Executor;

import cn.itcast.util.LocalDateUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleWithFixedDelay(() -> System.out.println("执行任务" + LocalDateUtils.getLocalDateTimeStr()),
                10,
                5,
                TimeUnit.SECONDS);
        service.scheduleAtFixedRate(() -> System.out.println("执行任务" + LocalDateUtils.getLocalDateTimeStr()),
                10,
                5,
                TimeUnit.SECONDS);
    }

}
