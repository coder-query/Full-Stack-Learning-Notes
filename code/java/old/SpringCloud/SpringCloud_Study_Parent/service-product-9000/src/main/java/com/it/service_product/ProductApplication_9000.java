package com.it.service_product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient  // 这个更具有通用性,可以跟nacos、zookeepor注册
//@EnableEurekaClient   // 将当前项目作为Eureka Client 注册到Eureka Server中
@SpringBootApplication
@MapperScan("com.it.service_product.mapper")
public class ProductApplication_9000{

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication_9000.class, args);
    }

}