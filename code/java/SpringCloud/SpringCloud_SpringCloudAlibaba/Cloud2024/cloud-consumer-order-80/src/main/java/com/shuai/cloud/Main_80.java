package com.shuai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main_80 {
    public static void main(String[] args) {
        SpringApplication.run(Main_80.class, args);
    }
}
