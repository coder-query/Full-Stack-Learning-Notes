package com.it.static_page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  // 这个更具有通用性,可以跟nacos、zookeepor注册
//@EnableEurekaClient   // 将当前项目作为Eureka Client 注册到Eureka Server中
public class PageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class, args);
    }

    @Bean
    @LoadBalanced // 开启Ribbon负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}