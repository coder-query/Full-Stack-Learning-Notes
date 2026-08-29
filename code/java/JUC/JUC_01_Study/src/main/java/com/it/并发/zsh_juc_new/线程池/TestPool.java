package com.it.并发.zsh_juc_new.线程池;

import com.it.并发.zsh_juc_new.线程池.handler.ThreadPoolRejectHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
public class TestPool {
    public static void main(String[] args) {
        // 注意：我们new了线程池，那么线程池一开始是没有线程的
        // 只要执行execute方法，才会创建线程（worker）  对象 ---》 类
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                // 自定义拒绝策略（业务需求来实现）
                new ThreadPoolRejectHandler()
        );

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();


        // qddwfwf


        reentrantLock.lock();
        threadPoolExecutor.execute(() -> {
        });
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 并行
            Future<String> future = threadPoolExecutor.submit(() -> {
                return "结果";
            });
            futures.add(future);
        }

//        // 回顾创建线程的方式
//        new Thread(() -> {
//        });
    }
}
