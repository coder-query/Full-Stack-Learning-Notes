package com.service;

import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import com.job.fail.FailedJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

@Service
public class JobService2 {

    @Resource
    private Executor asyncExecutorPool1;  // 改为 Executor 类型
    public CompletableFuture<String> runJob() {
        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 开始执行");

        List<Runnable> importJobsList = createJobList();
        List<FailedJob> failedJobs = new CopyOnWriteArrayList<>();
        long startTime = System.currentTimeMillis();

        // 修改方法调用
        CompletableFuture<?>[] futures = submitAllTasks(importJobsList, asyncExecutorPool1, failedJobs);

        return CompletableFuture.allOf(futures).thenApplyAsync(v -> {
                    System.out.println("v = " + v);
                    long endTime = System.currentTimeMillis();
                    long costTime = endTime - startTime;
                    String result = buildResult(importJobsList.size(), failedJobs, costTime);

                    if (!failedJobs.isEmpty()) {
                        System.out.println("[" + Thread.currentThread().getName() + "] 正在重试失败任务...66666");
                        String retryResult = retryFailedJobs(failedJobs, asyncExecutorPool1);
                        result += "\n" + retryResult;
                    }
                    System.out.println("[" + Thread.currentThread().getName() + "] 所有任务处理完成");
                    return result;
                }, asyncExecutorPool1);
    }

    // 修改方法参数为 Executor
    private CompletableFuture<?>[] submitAllTasks(List<Runnable> jobs,
                                                  Executor executor,
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

    // 修改方法参数为 Executor
    private String retryFailedJobs(List<FailedJob> failedJobs, Executor executor) {
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

    // 其他方法保持不变...
    private List<Runnable> createJobList() {
        ImportFileJob2 importFileJob0 = new ImportFileJob2();
        ImportFileJob1 importFileJob1 = new ImportFileJob1();
        // ... 其他任务创建代码
        return new CopyOnWriteArrayList<Runnable>() {{
            add(importFileJob0);
            add(importFileJob1);
            // ... 其他任务添加
        }};
    }

    private String buildResult(int totalJobs, List<FailedJob> failedJobs, long costTime) {
        int successJobs = totalJobs - failedJobs.size();
        double successRate = totalJobs > 0 ? (successJobs * 100.0 / totalJobs) : 0;

        return String.format(
                "任务执行完成 - 总任务数: %d, 成功: %d, 失败: %d, 成功率: %.2f%%, 耗时: %dms",
                totalJobs, successJobs, failedJobs.size(), successRate, costTime
        );
    }
}