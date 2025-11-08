package com.shuai.controller;

import com.shuai.feign.ConsumerFeignService;
import com.shuai.feign.TestFeignDemo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class TestController {
    
    @Resource
    private ConsumerFeignService consumerFeignService;

    @Resource private TestFeignDemo testFeignDemo;
//
//    @GetMapping("/test-feign")
//    public String testFeign() {
//        System.out.println("Feign 被调用了。。。");
//        return consumerFeignService.helloNacosProvider();
//    }

    @GetMapping("/order/query")
    public String queryOrder() {
        System.out.println("queryOrder Feign 被调用了。。。");

        return consumerFeignService.queryOrder();
    }

    @GetMapping("/order/update")
    public String updateOrder() {
        System.out.println("updateOrder Feign 被调用了。。。");
        return consumerFeignService.updateOrder();
    }

    @RequestMapping("/getMessage")
    public String getMessage(@RequestParam(value = "name") String name) {
        return testFeignDemo.getMessage(name);
    }

//    @GetMapping("/order/add")
//    public String addOrder() {
//        System.out.println("addOrder Feign 被调用了。。。");
//        return consumerFeignService.addOrder();
//    }

    @RequestMapping("/send")
    public String send(HttpServletRequest httpServletRequest) {
//        "DETAILS_USER_ID",   // 对应网关添加的用户ID头
//                "DETAILS_USERNAME",  // 对应网关添加的用户名头
//                "USER_KEY"
        String detailsUserIdValue = httpServletRequest.getHeader("DETAILS_USER_ID");
        System.out.println("detailsUserIdValue = " + detailsUserIdValue);
        String detailsUsernameValue = httpServletRequest.getHeader("DETAILS_USERNAME");
        System.out.println("detailsUsernameValue = " + detailsUsernameValue);
        String userKeyValue = httpServletRequest.getHeader("USER_KEY");
        System.out.println("userKeyValue = " + userKeyValue);
        return testFeignDemo.send();
    }
}