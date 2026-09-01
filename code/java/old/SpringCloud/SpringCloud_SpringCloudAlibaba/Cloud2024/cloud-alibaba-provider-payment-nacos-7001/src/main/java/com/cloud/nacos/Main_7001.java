package com.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Main_7001 {
    public static void main(String[] args) {
        SpringApplication.run(Main_7001.class, args);
        // 冒泡排序
    }
}