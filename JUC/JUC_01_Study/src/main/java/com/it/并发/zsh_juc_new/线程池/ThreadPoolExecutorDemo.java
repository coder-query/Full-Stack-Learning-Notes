package com.it.并发.zsh_juc_new.线程池;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                1,
//                1,
//                0,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//        );

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                1,
//                1,
//                0,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        );

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                1,
//                1,
//                0,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//        );

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                1,
//                1,
//                0,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardPolicy()
//        );

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        // 自定义拒绝策略
        
        // 3个任务，for 3次
        try {
            // for 循环
            for (int i = 0; i < 3; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("Thread.currentThread().getName() -- {}", Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            log.error("发生异常，e", e);
            // 补救错失
        }
        log.info("main -- {}", Thread.currentThread().getName());

    }
}