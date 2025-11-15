package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@SpringBootApplication
public class ApplicationBoot {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ApplicationBoot.class, args);
    }

    @Bean(name = "asyncExecutorPool1")
    public ThreadPoolTaskExecutor asyncExecutorPool1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);   // 根据任务量调整
        executor.setMaxPoolSize(5);    // 根据峰值调整
        executor.setQueueCapacity(200); // 根据内存和任务特性调整
        executor.setKeepAliveSeconds(60); // 空闲线程存活时间
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("asyncExecutorPool-zsh-thread-");
        executor.setWaitForTasksToCompleteOnShutdown(true); // 优雅关闭
        executor.setAwaitTerminationSeconds(60); // 等待任务完成的最大时间
        executor.initialize();
        return executor;
    }
    @Bean(name = "asyncExecutorPool2")
    public ThreadPoolTaskExecutor asyncExecutorPool2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);   // 根据任务量调整
        executor.setMaxPoolSize(5);    // 根据峰值调整
        executor.setQueueCapacity(200); // 根据内存和任务特性调整
        executor.setKeepAliveSeconds(60); // 空闲线程存活时间
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("asyncExecutorPool-zsh-thread-");
        executor.setWaitForTasksToCompleteOnShutdown(true); // 优雅关闭
        executor.setAwaitTerminationSeconds(60); // 等待任务完成的最大时间
        executor.initialize();
        return executor;
    }
}
