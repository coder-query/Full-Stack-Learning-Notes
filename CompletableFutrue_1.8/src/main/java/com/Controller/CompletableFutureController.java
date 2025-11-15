package com.Controller;

import com.service.CompletableFutureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
public class CompletableFutureController {

    @Resource
    private
    CompletableFutureService completableFutureService;
    @RequestMapping("/testCompletableFuture")
    public String testCompletableFuture() {
//        CompletableFuture<String> stringCompletableFuture = completableFutureService.testCompletableFuture();
        return completableFutureService.testCompletableFuture();
    }


}