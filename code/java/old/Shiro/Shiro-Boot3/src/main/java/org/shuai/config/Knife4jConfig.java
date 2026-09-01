package org.shuai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("基于Shiro + Springboot3 开发 API文档")
                        .description("基于Spring Boot 3.2.0 + Shiro 2.1.0 + MyBatis-Flex 1.11.6 开发")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("shuaihong-coding")
                                .email("2798679648@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    /** 系统功能 */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("System")
                .packagesToScan("org.shuai.sys.controller")
                .build();
    }
}
