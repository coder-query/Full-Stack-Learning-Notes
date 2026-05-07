package org.shuai.boot.shirocodestudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.shuai.boot.shirocodestudy.sys.mapper"})
public class ShiroCodeStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroCodeStudyApplication.class, args);
    }

}
