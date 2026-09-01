package com.it.并发.zsh_juc_new.线程池;

import java.time.Instant;
import java.util.concurrent.*;

public class Excutors_newScheduleThreadPool4 {
    /**
     * 线程池
     *
     * @param args 注意 : 临时线程 = 最大线程 - 核心线程
     *             1. 核心线程数
     *             2. 最大线程数
     *             3. 临时线程空闲时的最大存活时间 (值)
     *             4. 临时线程空闲时的最大存活时间 (单位)
     *             5. 任务阻塞队列
     *             6. 创建线程工厂 --> 一般底层默认线程工厂
     *             7. 任务的拒绝策略
     */
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        // 正常执行
        scheduledExecutorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "：1");
        });

        // 延迟执行，执行当前任务延迟5s后再执行
//        pool.schedule(() -> {
//            System.out.println(Thread.currentThread().getName() + "：2");
//        },5,TimeUnit.SECONDS);

        // 周期执行，当前任务第一次延迟5s执行，然后没3s执行一次
        // 这个方法在计算下次执行时间时，是从任务刚刚开始时就计算。
//        pool.scheduleAtFixedRate(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis() + "：3");
//        },2,1,TimeUnit.SECONDS);

        // 周期执行，当前任务第一次延迟5s执行，然后没3s执行一次
        // 这个方法在计算下次执行时间时，会等待任务结束后，再计算时间
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis() + "：3");
//        }, 2, 1, TimeUnit.SECONDS);
    }
}