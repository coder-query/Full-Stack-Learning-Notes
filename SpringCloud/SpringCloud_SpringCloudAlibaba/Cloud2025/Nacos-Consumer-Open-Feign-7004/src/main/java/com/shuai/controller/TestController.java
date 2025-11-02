package com.shuai.controller;

import com.shuai.feign.ConsumerFeignService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Resource
    private ConsumerFeignService consumerFeignService;
    
    @GetMapping("/test-feign")
    public String testFeign() {
        System.out.println("Feign 被调用了。。。");
        return consumerFeignService.helloNacosProvider();
    }
}