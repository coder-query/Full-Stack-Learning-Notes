package org.shuai.boot.config;

import org.shuai.boot.condition.ConditionalMissBean;
import org.shuai.boot.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration // 等于子xml文件
@ComponentScan(basePackages = "org.shuai.boot.user")
public class UserConfig {

    @Bean
    @Conditional(value = ConditionalMissBean.class)  // ++> @ConditionalOnMissingBean
    public UserService zzzzxxxx() {
        System.out.println("UserConfig 中 创建了 UserService");
        return new UserService();
    }
}
