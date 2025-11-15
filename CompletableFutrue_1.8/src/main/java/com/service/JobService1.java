package com.service;

import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import com.job.fail.FailedJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class JobService1 {

    @Autowired
    @Qualifier("asyncExecutorPool")
    private ThreadPoolTaskExecutor asyncExecutorPool;

//    @Async(value = "asyncExecutorPool")
    public String runJob() {
        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 开始执行");

        // 直接使用任务列表，不再创建自定义线程池
        List<Runnable> importJobsList = createJobList();

        // 存储失败的任务及其异常
        List<FailedJob> failedJobs = new CopyOnWriteArrayList<>();
        long startTime = System.currentTimeMillis();

        // 使用 Spring 的异步线程池执行任务
        int jobSize = importJobsList.size();
        CompletableFuture<?>[] futures = new CompletableFuture[jobSize];

        importJobsList.forEach(importFileJob -> {
            int index = importJobsList.indexOf(importFileJob);
            // 关键修改：显式传入 Spring 线程池
            futures[index] = CompletableFuture.runAsync(importFileJob, asyncExecutorPool.getThreadPoolExecutor())
                    .exceptionally(throwable -> {
                        FailedJob failedJob = new FailedJob(importFileJob, throwable);
                        failedJobs.add(failedJob);
                        System.err.println("[" + Thread.currentThread().getName() + "] 任务执行失败: " + importFileJob + ", 异常: " + throwable.getMessage());
                        return null;
                    });
        });

        // 等待所有任务完成
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
                retryFailedJobs(failedJobs);
            }
        }

        System.out.println("[" + Thread.currentThread().getName() + "] main over");

        return "success";
    }
    private List<Runnable> createJobList() {
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

        return new CopyOnWriteArrayList<Runnable>() {{
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
    private void handleFailedJobs(List<FailedJob> failedJobs) {
        System.out.println("[" + Thread.currentThread().getName() + "] 失败任务详情:");
        failedJobs.forEach(failedJob -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 任务: " + failedJob.getJob() +
                    ", 异常: " + failedJob.getThrowable().getMessage());
        });

        // 重试失败任务（使用相同的线程池）
        System.out.println("[" + Thread.currentThread().getName() + "] 重新执行失败任务...");
        retryFailedJobs(failedJobs);
    }

    private void retryFailedJobs(List<FailedJob> failedJobs) {
        List<CompletableFuture<?>> retryFutures = failedJobs.stream()
                .map(failedJob -> CompletableFuture.runAsync(failedJob.getJob(), asyncExecutorPool.getThreadPoolExecutor())
                        .exceptionally(throwable -> {
                            System.err.println("[" + Thread.currentThread().getName() + "] 重试任务仍然失败: " + failedJob.getJob() +
                                    ", 异常: " + throwable.getMessage());
                            return null;
                        }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(retryFutures.toArray(new CompletableFuture[0])).join();
    }
}