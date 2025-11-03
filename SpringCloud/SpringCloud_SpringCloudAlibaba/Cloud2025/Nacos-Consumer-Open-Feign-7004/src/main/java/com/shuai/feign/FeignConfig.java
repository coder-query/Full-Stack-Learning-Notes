package com.shuai.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public feign.Contract feignContract() {
        return new feign.Contract.Default();
    }
}