package com.example.spring_security_study.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/12 0012
 */
// @Configuration
public class MemoryUserDetailsManager {
    /**
     * 创建 基于内存的用户信息管理器
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 创建基于内存的用户信息管理器
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("zsh")
                        .password("123456")
                        .roles("USER").build());
        return inMemoryUserDetailsManager;
    }
}
