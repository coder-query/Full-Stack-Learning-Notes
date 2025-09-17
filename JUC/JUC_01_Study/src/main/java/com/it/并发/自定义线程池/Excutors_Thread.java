package com.it.并发.自定义线程池;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Excutors_Thread {
    /**
     * 线程池
     * @param args
     * 注意 : 临时线程 = 最大线程 - 核心线程
     * 1. 核心线程数
     * 2. 最大线程数
     * 3. 临时线程空闲时的最大存活时间 (值)
     * 4. 临时线程空闲时的最大存活时间 (单位)
     * 5. 任务阻塞队列
     * 6. 创建线程工厂 --> 一般底层默认线程工厂
     * 7. 任务的拒绝策略
     */
    public static void main(String[] args) {
        /// 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3, /// 核心线程数
                5, /// 最大线程数
                1, /// 临时线程空闲时的最大存活时间 (值)
                TimeUnit.SECONDS, /// 临时线程空闲时的最大存活时间 (单位)
                new LinkedBlockingDeque<>(3), /// 任务阻塞队列
                new ThreadPoolExecutor.DiscardOldestPolicy() /// 任务的拒绝策略
        );

        ///调用线程池
//        poolExecutor.submit(()->{
//            for (int i = 0; i < 100; i++){
//                System.out.println(Thread.currentThread().getName() + "--->" + i);
//            }
//        });
        poolExecutor.execute(()->{
            System.out.println(Thread.currentThread().getName() + "--->" + 666);
            throw new NullPointerException("异常");
        });
//        poolExecutor.submit()

        /// 销毁线程池
        poolExecutor.shutdown();
        poolExecutor.shutdownNow();
    }
}
