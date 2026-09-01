package com.mdk.springsecurity6study.config.security;

import com.mdk.springsecurity6study.service.impl.user.SysUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SysUserDetailsService sysUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 配置密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证提供者
     *  认证提供者：sysUserDetailsService 和 加密 功能进行整合
     */
    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(sysUserDetailsService);  // 设置UserDetailsService
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);  // 设置密码编码器
        return daoAuthenticationProvider;
    }

    /**
     * 配置AuthenticationManager ： 负责认证
     *
     * 推荐的方式：使用 AuthenticationConfiguration
     * 优点：Spring Security 会自动处理所有已注册的 AuthenticationProvider
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        // Spring Security 会自动使用我们配置的AuthenticationProvider
//        return config.getAuthenticationManager();
//    }

    /**
     * 配置安全过滤器链 - Spring Security 6推荐方式
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // 配置授权规则
                .authorizeHttpRequests(auth -> auth
                        // 公开访问的路径
                        .requestMatchers(
                                "/auth/**",
                                "/error",
                                "/favicon.ico",
                                // swagger / knife4j 文档
                                "/doc.html",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                                )
                        .permitAll()
                        // 需要认证的路径
                        .anyRequest()
                        .authenticated()
                )

                // 设置Session管理策略
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 无状态，适合JWT
                )

                // 添加 JWT 过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}