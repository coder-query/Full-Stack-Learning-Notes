package com.it.并发.zsh_juc_new.线程池;

import java.time.Instant;
import java.util.concurrent.*;

public class Excutors_newCachedThreadPool {
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
    public static void main(String[] args) {
        // TODO submit 和 execute 的区别
        // 古法编程  ---》 CompleteFutrue之前，原始操作线程池任务

        CountDownLatch countDownLatch = new CountDownLatch(3);
        long startTime = Instant.now().toEpochMilli();
        // newFixedThreadPool(创建固定的线程池大小)
        ExecutorService executorService = Executors.newCachedThreadPool(); // cache 表示缓存线程
//
        Future<String> submit = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + "--->" + 666);
            // 去查询看sql，封装成了实体对象，return 实体对象
            return "233456789";
        });


        try {
            String res = submit.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}


//        executorService.execute(() -> {
//                    try {
//                        // 模拟去数据库里查数据
//                        Thread.sleep(3000L);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println(Thread.currentThread().getName() + "--->" + 666);
//                    countDownLatch.countDown();
//                }
//        );
//        executorService.execute(() -> {
//                    try {
//                        // 模拟去数据库里查数据
//                        Thread.sleep(3000L);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println(Thread.currentThread().getName() + "--->" + 777);
//                    countDownLatch.countDown();
//                }
//        );
//        executorService.execute(() -> {
//                    // 模拟去数据库里查数据
//                    try {
//                        Thread.sleep(3000L);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println(Thread.currentThread().getName() + "--->" + 777);
//                    countDownLatch.countDown();
//                }
//        );
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("main 方法执行完毕,耗时" + (Instant.now().toEpochMilli() - startTime + "ms"));
//