package com.it;

import com.it.service.HelloWorldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import cn.hutool.http.HttpUtil;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@ImportResource(value = "classpath:application-context.xml")
public class SpringbootStudyApplication {

    public static void main(String[] args) {
        // 启动Spring Boot
        ConfigurableApplicationContext ioc = SpringApplication.run(SpringbootStudyApplication.class, args); // Spring3.0
        HelloWorldService bean = ioc.getBean(HelloWorldService.class);
        System.out.println(bean);
    }
}