package com.service;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {
    @Resource
    private JobService1 jobService1;

    @Resource
    private JobService2 jobService2;

    public String testCompletableFuture() {
        return jobService1.runJob();
    }

}
