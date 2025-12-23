package org.example.bootmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.bootmybatisplus.mapper")
public class BootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisPlusApplication.class, args);
    }

}
