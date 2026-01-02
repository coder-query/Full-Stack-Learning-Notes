package com.mdk.springsecurity6study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mdk.springsecurity6study.mapper")
public class SpringSecurity6StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity6StudyApplication.class, args);
    }

}
