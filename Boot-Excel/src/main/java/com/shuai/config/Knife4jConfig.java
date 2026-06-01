package com.shuai.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
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
                        .title("ai智能媒体素材平台API文档")
                        .description("基于Spring Boot 3.2.0+ Sa-Token + MyBatis-Plus开发的ai智能媒体系统")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("shuaihong-coding")
                                .email("2798679648@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                // 配置Sa-Token的JWT认证
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .description("Sa-Token认证，格式: token值")));
    }

    /**
     * EasyPoi分组
     */
    @Bean
    public GroupedOpenApi easyPoiApi() {
        return GroupedOpenApi.builder()
                .group("EasyPoi")
                .pathsToMatch("/easyPoi/**")
                .build();
    }

    /**
     * EasyExcel分组
     */
    @Bean
    public GroupedOpenApi easyExcelApi() {
        return GroupedOpenApi.builder()
                .group("EasyExcel")
                .pathsToMatch("/easyExcel/**")
                .build();
    }

}
