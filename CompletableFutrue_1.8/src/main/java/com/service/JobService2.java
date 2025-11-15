package com.service;


import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import com.job.fail.FailedJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class JobService2 {

    @Async(value = "asyncExecutorPool")
    public String runJob() {

        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 开始执行");

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
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

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
        CopyOnWriteArrayList<Runnable> importJobsList = new CopyOnWriteArrayList<Runnable>() {{
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
        }};


        // 存储失败的任务及其异常
        List<FailedJob> failedJobs = new CopyOnWriteArrayList<>();
        long startTime = System.currentTimeMillis();

        // 创建CompletableFuture数组来跟踪所有任务
        int jobSize = importJobsList.size();
        CompletableFuture<?>[] futures = new CompletableFuture[jobSize];

        importJobsList.forEach(importFileJob -> {
            int index = importJobsList.indexOf(importFileJob);
            futures[index] = CompletableFuture.runAsync(importFileJob, threadPoolExecutor)
                    .exceptionally(throwable -> {
                        // 捕获异常并记录失败的任务
                        FailedJob failedJob = new FailedJob(importFileJob, throwable);
                        failedJobs.add(failedJob);
                        System.err.println("[" + Thread.currentThread().getName() + "] exceptionally 捕捉到任务执行失败: " + importFileJob + ", 异常: " + throwable.getMessage());
                        return null;
                    });
        });

        // 等待所有CompletableFuture完成
        System.out.println("[" + Thread.currentThread().getName() + "] 等待所有任务完成...");
        CompletableFuture.allOf(futures).join();

        long endTime = System.currentTimeMillis();

        // 输出统计信息
        System.out.println("[" + Thread.currentThread().getName() + "] 总任务数: " + jobSize);
        System.out.println("[" + Thread.currentThread().getName() + "] 成功任务数: " + (jobSize - failedJobs.size()));
        System.out.println("[" + Thread.currentThread().getName() + "] 失败任务数: " + failedJobs.size());
        System.out.println("[" + Thread.currentThread().getName() + "] cost time: " + (endTime - startTime) / 1000 + "s");

        // 如果有失败的任务，输出详细信息
        if (!failedJobs.isEmpty()) {
            System.out.println("[" + Thread.currentThread().getName() + "] 失败任务详情:");
            failedJobs.forEach(failedJob -> {
                System.out.println("[" + Thread.currentThread().getName() + "] 任务: " + failedJob.getJob() +
                        ", 异常: " + failedJob.getThrowable().getMessage());
            });

            // 可以选择重新执行失败的任务
            System.out.println("[" + Thread.currentThread().getName() + "] 重新执行失败任务...");
            int retryCount = 3;
            for (int i = 0; i < retryCount; i++) {
                System.out.println("[" + Thread.currentThread().getName() + "] 正在重新执行失败任务...第" + (i + 1) + "次...");
                retryFailedJobs(failedJobs, threadPoolExecutor);
            }
        }

        System.out.println("[" + Thread.currentThread().getName() + "] main over");

        // 关闭线程池
        threadPoolExecutor.shutdown();

        return "success";
    }
    // 重新执行失败的任务
    private static void retryFailedJobs(List<FailedJob> failedJobs, ThreadPoolExecutor executor) {
        List<CompletableFuture<?>> retryFutures = failedJobs.stream()
                .map(failedJob -> CompletableFuture.runAsync(failedJob.getJob(), executor)
                        .exceptionally(throwable -> {
                            System.err.println("[" + Thread.currentThread().getName() + "] 重试任务仍然失败: " + failedJob.getJob() +
                                    ", 异常: " + throwable.getMessage());
                            return null;
                        }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(retryFutures.toArray(new CompletableFuture[0])).join();
    }
}
