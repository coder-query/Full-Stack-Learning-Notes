package com.shuai.springboot3_http_client.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j接口文档配置
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API文档")
                        .description("基于Spring Boot 3.5.9 开发")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("xxxxxx")
                                .email("27xxxxx648@qq.com")));
    }
}
