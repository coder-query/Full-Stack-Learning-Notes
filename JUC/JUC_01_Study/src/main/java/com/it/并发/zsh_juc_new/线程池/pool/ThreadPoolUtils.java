package com.it.并发.zsh_juc_new.线程池.pool;

import com.it.并发.zsh_juc_new.线程池.handler.ThreadPoolRejectHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义拒绝策略
 * 在创建线程池的时候，写 lambda
 * 实现 RejectedExecutionHandler 接口，重写rejectedExecution方法
 *
 */
public class ThreadPoolUtils {
    public static ThreadPoolExecutor createTheadPool() {
        /**
         *  在创建线程池的时候，写 lambda
         */
//        return new ThreadPoolExecutor(
//                5,
//                5,
//                0,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(10),
//                (r, executor) -> {
//                    System.out.println("自定义拒绝策略");
//                }
//        );

        final AtomicInteger atomicInteger = new AtomicInteger();
        return new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                (r) -> {
                    Thread thread = new Thread(r);
                    thread.setName("自定义线程--" + atomicInteger.addAndGet(1));
                    return thread;
                },
                // 自定义拒绝策略（业务需求来实现）
                new ThreadPoolRejectHandler()
        );
    }

    // 本地重试，新的线程池---》 写入sql---》 xxl-job
    public static ThreadPoolExecutor retryExecutor() {
        return new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );
    }

    // 关闭线程池
    public static void shutdown(ThreadPoolExecutor executor) {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
