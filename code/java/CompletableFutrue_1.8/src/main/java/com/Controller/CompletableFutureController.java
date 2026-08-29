package com.Controller;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.service.CompletableFutureService;
import com.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CompletableFutureController {

    @Resource
    private
    CompletableFutureService completableFutureService;
    @RequestMapping("/testCompletableFuture")
    public String testCompletableFuture() {
//        CompletableFuture<String> stringCompletableFuture = completableFutureService.testCompletableFuture();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "ThreadLocalUtil.get()";
        },fixedThreadPool);
        completableFuture1.join();

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("ThreadLocalUtil.get()");
        },fixedThreadPool);
        completableFuture1.join();

        return completableFutureService.testCompletableFuture();
    }


}