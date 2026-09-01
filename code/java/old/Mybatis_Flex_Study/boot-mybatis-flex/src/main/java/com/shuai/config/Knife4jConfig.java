package com.shuai.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    @Bean
    public Docket redisSetNxApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("redis的setnx实现分布式锁")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.shuai.controller.setnx"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket RedissonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Redisson的分布式锁")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.shuai.controller.redisson"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket redisMQApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("redis实现mq消息队列")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.shuai.controller.redismq"))
                .paths(PathSelectors.any())
                .build();
    }

    //com/shuai/controller
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("shuai")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shuai.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot redis API文档")
                .description("Knife4j 多分组接口文档")
                .version("1.0.0")
                .contact(new Contact("帅宏-coding", "https://gitee.com/zhangshuaihong", "2798679648@qq.com"))
                .build();
    }
}
