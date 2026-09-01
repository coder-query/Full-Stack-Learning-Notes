package com.shuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/4 0004
 */
@SpringBootApplication
@MapperScan("com.shuai.it.mapper")
public class SpringbootApplicationShardingJdbcSimple {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationShardingJdbcSimple.class, args);
    }
}
