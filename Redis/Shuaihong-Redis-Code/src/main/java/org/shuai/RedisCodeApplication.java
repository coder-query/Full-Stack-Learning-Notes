package org.shuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.shuai.mapper")
public class RedisCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCodeApplication.class, args);
    }
}
