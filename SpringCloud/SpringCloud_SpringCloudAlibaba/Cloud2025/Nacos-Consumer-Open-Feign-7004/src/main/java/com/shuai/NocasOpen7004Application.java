package com.shuai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.shuai.**")
@SpringBootApplication
public class NocasOpen7004Application {
    public static void main(String[] args) {
        SpringApplication.run(NocasOpen7004Application.class, args);
    }
}
