package org.shuai.boot.shirocodestudy.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.shuai.boot.shirocodestudy.shiro.filter.JwtAuthFilter;
import org.shuai.boot.shirocodestudy.shiro.realm.CustomRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    public static final String JWT_AUTH_FILTER = "jwtAuthFilter";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put(JWT_AUTH_FILTER,  new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/doc.html", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/v2/api-docs/**", "anon");
        filterChainMap.put("/auth/**", "anon");
        filterChainMap.put("/**", JWT_AUTH_FILTER);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        return defaultWebSecurityManager;
    }

    /**
     * Shiro 生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Shiro 授权注解切面，拦截 @RequiresRoles、@RequiresPermissions 等
     * 注意：需要配合 spring-boot-starter-aop 使用（由其提供 Advisor 自动代理）
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
