package com.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService3 {

    @Async(value = "asyncExecutorPool1")
    public String runJob() {
        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 启动");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[" + Thread.currentThread().getName() + "] JobService.runJob() 结束");
        return "success";
    }
}
