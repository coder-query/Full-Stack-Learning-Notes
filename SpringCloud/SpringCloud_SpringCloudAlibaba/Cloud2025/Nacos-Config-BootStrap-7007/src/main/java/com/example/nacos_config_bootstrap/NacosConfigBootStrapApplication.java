package com.example.nacos_config_bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigBootStrapApplication {

  public static void main(String[] args) {
    SpringApplication.run(NacosConfigBootStrapApplication.class, args);
  }
}
