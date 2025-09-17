package com.it.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 14:52
 */
@Configuration
public class OrderConfig {



//    openFeign重试机制
    @Bean
    Retryer retryer(){
        return new Retryer.Default();
    }

//    openFeign配置日志
    @Bean
    Logger.Level feignLoggerLevel(){
      return Logger.Level.FULL;
    }

    @LoadBalanced // 开启负载均衡
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
