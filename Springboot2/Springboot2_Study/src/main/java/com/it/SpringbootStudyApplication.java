package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import cn.hutool.http.HttpUtil;

@SpringBootApplication
public class SpringbootStudyApplication {

    public static void main(String[] args) {
//        // 在Spring Boot启动前设置系统属性
        System.setProperty("hutool.http.client.impl", "cn.hutool.http.client.apache.ApacheHttpClient");

        // 启动Spring Boot
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootStudyApplication.class, args);
    }
}