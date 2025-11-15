package com.service;

import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import com.job.fail.FailedJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class JobService2 {

    @Async(value = "asyncExecutorPool1")
    public CompletableFuture<String> runJob() {
        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 开始执行");

        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = createThreadPool();

        // 创建任务列表
        List<Runnable> importJobsList = createJobList();

        // 存储失败的任务
        List<FailedJob> failedJobs = new CopyOnWriteArrayList<>();

        // 开始时间
        long startTime = System.currentTimeMillis();

        // 提交所有任务并返回Future数组
        CompletableFuture<?>[] futures = submitAllTasks(importJobsList, threadPoolExecutor, failedJobs);

        // 返回一个CompletableFuture，当所有任务完成时完成
        return CompletableFuture.allOf(futures)
                .thenApplyAsync(v -> {
                    // 所有任务完成后的回调
                    long endTime = System.currentTimeMillis();
                    long costTime = endTime - startTime;

                    // 构建详细的返回信息
                    String result = buildResult(importJobsList.size(), failedJobs, costTime);

                    // 如果有失败任务，进行重试
                    if (!failedJobs.isEmpty()) {
                        System.out.println("[" + Thread.currentThread().getName() + "] 正在重试失败任务...");
                        String retryResult = retryFailedJobs(failedJobs, threadPoolExecutor);
                        result += "\n" + retryResult;
                    }
                    System.out.println("[" + Thread.currentThread().getName() + "] 所有任务处理完成");
                    return result;
                }, threadPoolExecutor)
                .whenComplete((result, throwable) -> {
                    // 确保关闭线程池
                    threadPoolExecutor.shutdown();
                    if (throwable != null) {
                        System.err.println("[" + Thread.currentThread().getName() + "] 任务执行出现异常: " + throwable.getMessage());
                    }
                });
    }

    private ThreadPoolExecutor createThreadPool() {
        AtomicInteger threadCounter = new AtomicInteger(0);
        return new ThreadPoolExecutor(
                5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("CustomPool-zsh-thread---" + threadCounter.incrementAndGet());
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
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
        }};
    }

    private CompletableFuture<?>[] submitAllTasks(List<Runnable> jobs,
                                                  ThreadPoolExecutor executor,
                                                  List<FailedJob> failedJobs) {

        CompletableFuture<?>[] futures = new CompletableFuture[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            final Runnable job = jobs.get(i);
            final int index = i;

            futures[index] = CompletableFuture.runAsync(job, executor)
                    .exceptionally(exception -> {
                        FailedJob failedJob = new FailedJob(job, exception);
                        failedJobs.add(failedJob);
                        System.err.println("[" + Thread.currentThread().getName() + "] 任务执行失败: " + job + ", 异常: " + exception.getMessage());
                        return null;
                    });
        }
        return futures;
    }

    private String buildResult(int totalJobs, List<FailedJob> failedJobs, long costTime) {
        int successJobs = totalJobs - failedJobs.size();
        double successRate = totalJobs > 0 ? (successJobs * 100.0 / totalJobs) : 0;

        return String.format(
                "任务执行完成 - 总任务数: %d, 成功: %d, 失败: %d, 成功率: %.2f%%, 耗时: %dms",
                totalJobs, successJobs, failedJobs.size(), successRate, costTime
        );
    }

    private String retryFailedJobs(List<FailedJob> failedJobs, ThreadPoolExecutor executor) {
        System.out.println("[" + Thread.currentThread().getName() + "] 开始重试失败任务...");

        List<FailedJob> stillFailedAfterRetry = new CopyOnWriteArrayList<>();
        long retryStartTime = System.currentTimeMillis();

        CompletableFuture<?>[] retryFutures = failedJobs.stream()
                .map(failedJob -> CompletableFuture.runAsync(failedJob.getJob(), executor)
                        .exceptionally(throwable -> {
                            FailedJob retryFailedJob = new FailedJob(failedJob.getJob(), throwable);
                            stillFailedAfterRetry.add(retryFailedJob);
                            System.err.println("[" + Thread.currentThread().getName() + "] 重试任务失败: " + failedJob.getJob());
                            return null;
                        }))
                .toArray(CompletableFuture[]::new);

        // 等待重试完成
        CompletableFuture.allOf(retryFutures).join();

        long retryEndTime = System.currentTimeMillis();
        long retryCostTime = retryEndTime - retryStartTime;

        int retrySuccess = failedJobs.size() - stillFailedAfterRetry.size();
        double retrySuccessRate = failedJobs.size() > 0 ? (retrySuccess * 100.0 / failedJobs.size()) : 0;

        return String.format(
                "重试结果 - 重试任务数: %d, 重试成功: %d, 重试失败: %d, 重试成功率: %.2f%%, 重试耗时: %dms",
                failedJobs.size(), retrySuccess, stillFailedAfterRetry.size(), retrySuccessRate, retryCostTime
        );
    }
}