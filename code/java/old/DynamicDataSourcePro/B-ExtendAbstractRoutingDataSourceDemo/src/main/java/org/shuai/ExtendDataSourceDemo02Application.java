package org.shuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.shuai.**.mapper")
@SpringBootApplication
public class ExtendDataSourceDemo02Application {
    public static void main(String[] args) {
        SpringApplication.run(ExtendDataSourceDemo02Application.class, args);
    }

}
