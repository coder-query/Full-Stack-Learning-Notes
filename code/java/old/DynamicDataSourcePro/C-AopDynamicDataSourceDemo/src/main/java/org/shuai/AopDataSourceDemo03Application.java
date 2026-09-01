package org.shuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.shuai.**.mapper")
@SpringBootApplication
public class AopDataSourceDemo03Application {
    public static void main(String[] args) {
        SpringApplication.run(AopDataSourceDemo03Application.class, args);
    }

}
