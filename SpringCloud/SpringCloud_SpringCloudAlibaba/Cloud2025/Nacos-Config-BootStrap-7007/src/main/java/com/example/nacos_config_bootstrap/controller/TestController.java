package com.example.nacos_config_bootstrap.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class TestController {

    @Value(value = "${zsh.datasource.url}")
    private String url;
    @RequestMapping("/test")
    public String test() {
        return "测试成功。。。" +  url;
    }
}
