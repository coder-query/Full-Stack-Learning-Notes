package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import cn.hutool.http.HttpUtil;

@SpringBootApplication
public class SpringbootStudyApplication {

    public static void main(String[] args) {
        // 启动Spring Boot
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootStudyApplication.class, args);
    }
}