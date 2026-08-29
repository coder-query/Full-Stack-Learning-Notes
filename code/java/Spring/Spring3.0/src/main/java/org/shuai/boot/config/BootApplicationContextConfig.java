package org.shuai.boot.config;


import org.shuai.boot.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration   // 等同于根xml
@Import({UserConfig.class, OrderConfig.class})
public class BootApplicationContextConfig {

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

}
