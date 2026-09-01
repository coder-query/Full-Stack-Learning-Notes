package com.shuai.cloud.consumer.controller;

import com.commons.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/6 0006
 */
@RestController
public class TestController {
    @Value("${zsh.consul.provider-name}")
    public static String PaymentSrv_URL; //服务注册中心上的微服务名称
    
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/test/load")
    public String testLoad() {
        return restTemplate.getForObject(PaymentSrv_URL + "/test/loadBalance", String.class);
    }
}
