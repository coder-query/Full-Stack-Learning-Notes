package org.example.mybatisbatchtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.mybatisbatchtest.mapper")
public class MybatisBatchTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisBatchTestApplication.class, args);
    }

}
