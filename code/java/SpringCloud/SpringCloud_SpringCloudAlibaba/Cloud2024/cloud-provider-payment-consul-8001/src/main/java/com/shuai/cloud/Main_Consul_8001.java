package com.shuai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@SpringBootApplication
@MapperScan("com.shuai.cloud.provider.mapper")
@EnableDiscoveryClient
public class Main_Consul_8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Consul_8001.class, args);
    }
}
