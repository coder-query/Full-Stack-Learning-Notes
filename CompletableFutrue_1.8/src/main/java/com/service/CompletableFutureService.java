package com.service;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {
//    @Resource
//    private JobService1 jobService1;

    @Resource
    private JobService2 jobService2;

    @Resource
    private
    AsyncJobPoolService asyncJobPoolService;


    public String testCompletableFuture() {
//        return jobService1.runJob();
        String runJobResult = asyncJobPoolService.runJob();
        System.out.println("我是CompletableFutureService服务层。。。。，现在在等上面任务执行");
        System.out.println("请注意，现在代码已经执行到这里了哦，看一下上面的任务有没有返回值哦---runJobResult ： "+runJobResult);
        return runJobResult;
    }


}
