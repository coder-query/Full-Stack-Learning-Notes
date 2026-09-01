package com.shaui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.shaui.spring_security.mapper"})
public class SpringBootSecurityPracticeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityPracticeDemoApplication.class, args);
    }

}
