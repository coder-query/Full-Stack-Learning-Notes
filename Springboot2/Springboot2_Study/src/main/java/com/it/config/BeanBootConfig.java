package com.it.config;


import com.it.service.HelloWorldService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BeanBootConfig {

    @Bean
    public HelloWorldService helloWorldService() {
        return new HelloWorldService();
    }
}
