package com.Controller;

import com.service.CompletableFutureService;
import com.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

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