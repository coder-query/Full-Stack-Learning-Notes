package com.shuai.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.commons.apis") // 扫描 cloud-commons 中的 Feign 接口
public class MainOpenFeign_80 {
    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign_80.class, args);
    }
}
