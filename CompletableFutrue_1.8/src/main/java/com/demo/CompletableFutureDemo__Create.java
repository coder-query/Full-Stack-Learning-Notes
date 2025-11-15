package com.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.util.ThreadLocalUtil;
import com.util.TransmittableThreadUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureDemo__Create {
    public static void main(String[] args) {

        // 获取电脑的cpu核数
        System.out.println("cpu核数:" + Runtime.getRuntime().availableProcessors());

        // 使用原子计数器确保线程名称唯一
        AtomicInteger threadCounter = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("CustomPool-zsh-thread---" + threadCounter.incrementAndGet());
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());

        Executor ttlExecutor = TtlExecutors.getTtlExecutor(threadPoolExecutor);

        CompletableFuture<String> stringCompletableFutureB = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world 我在异步子线程里1   " + Thread.currentThread().getName());
            System.out.println("hello world 我在异步子线程里2   " + Thread.currentThread().getName());
            System.out.println("hello world 我在异步子线程里3   " + Thread.currentThread().getName());

            // 异常
            int num = 1 / 0;

            UUID userId = UUID.randomUUID();
            // 放入线程变量
            TransmittableThreadUtil.set(userId.toString());
            System.out.println("A任务都执行完毕，返回 A任务查到的数据userId = "  + userId);
            return userId.toString();
        }, ttlExecutor).exceptionally(throwable -> {
            System.out.println("B任务执行异常了，异常信息是：" + throwable.getMessage());
            return "B任务执行异常了，异常信息是：" + throwable.getMessage();
        });

        String resB = stringCompletableFutureB.join();
        System.out.println("B任务返回的数据是：" + resB);

        System.out.println("hello world 我在主线程里   " + Thread.currentThread().getName());

        Executor originalExecutor = TtlExecutors.unwrap(ttlExecutor);
        if (originalExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) originalExecutor).shutdown();
        }
    }
}
