package com.demo;

import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureDemo__Create {

    private ThreadPoolExecutor threadPoolExecutor = null;
    CopyOnWriteArrayList<Runnable> runnables = null;
    @Before
    public void before() {
        // 使用原子计数器确保线程名称唯一
        AtomicInteger threadCounter = new AtomicInteger(0);
     threadPoolExecutor = new ThreadPoolExecutor(
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

            ImportFileJob2 importFileJob0 = new ImportFileJob2();
            ImportFileJob1 importFileJob1 = new ImportFileJob1();
            ImportFileJob1 importFileJob2 = new ImportFileJob1();
            ImportFileJob1 importFileJob3 = new ImportFileJob1();
            ImportFileJob1 importFileJob4 = new ImportFileJob1();
            ImportFileJob1 importFileJob5 = new ImportFileJob1();
            ImportFileJob1 importFileJob6 = new ImportFileJob1();
            ImportFileJob1 importFileJob7 = new ImportFileJob1();
            ImportFileJob1 importFileJob8 = new ImportFileJob1();
            ImportFileJob1 importFileJob9 = new ImportFileJob1();
            ImportFileJob1 importFileJob10 = new ImportFileJob1();
            ImportFileJob1 importFileJob11 = new ImportFileJob1();
            ImportFileJob1 importFileJob12 = new ImportFileJob1();
            ImportFileJob1 importFileJob13 = new ImportFileJob1();
            ImportFileJob1 importFileJob14 = new ImportFileJob1();
            ImportFileJob1 importFileJob15 = new ImportFileJob1();

        runnables = new CopyOnWriteArrayList<Runnable>() {{
            add(importFileJob0);
            add(importFileJob1);
            add(importFileJob2);
            add(importFileJob3);
            add(importFileJob4);
            add(importFileJob5);
            add(importFileJob6);
            add(importFileJob7);
            add(importFileJob8);
            add(importFileJob9);
            add(importFileJob10);
            add(importFileJob11);
            add(importFileJob12);
            add(importFileJob13);
            add(importFileJob14);
            add(importFileJob15);
            // ... 添加其他任务
        }};

    }
    @Test
    public void test1() {
        CompletableFuture<?>[] futures = new CompletableFuture[runnables.size()];
        runnables.stream().forEach(runnable -> {
            futures[runnables.indexOf(runnable)] = CompletableFuture.runAsync(runnable, threadPoolExecutor);
        });
        CompletableFuture.allOf(futures).join();
        System.out.println("所有任务完成");
        threadPoolExecutor.shutdown();
        System.out.println("线程池已关闭");
    }

}
