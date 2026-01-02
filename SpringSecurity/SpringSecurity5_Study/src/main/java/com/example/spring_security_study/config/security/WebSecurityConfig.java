package com.example.spring_security_study.config.security;

import com.example.spring_security_study.config.security.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         *
         */
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        http.authorizeRequests(
                authorize -> authorize
                        .anyRequest()
                        .authenticated());

        /**
         * 登录 拦截器
         */
        http.formLogin(form -> {
            form.loginPage("/login")
                    .permitAll()
                    .successHandler(new MyAuthenticationSuccessHandler())  // 认证成功的处理
                    .failureHandler(new MyAuthenticationFailureHandler()); // 认证失败的处理
        });   //表单授权方式    ---> 基于html生成的登录页


        /**
         * 退出 拦截器
         */
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler()); //注销成功时的处理
        });
        /**
         *  会话管理
         */
        //会话管理
        http.sessionManagement(session -> {
            session.maximumSessions(1)
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });

        //错误处理
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint()); //请求未认证的接口
        });

        /**
         * 跨域 配置
         */
        //跨域
        http.cors(withDefaults());

        //关闭csrf攻击防御
        http.csrf((csrf) -> {
            csrf.disable();
        });

        return http.build();
    }
}