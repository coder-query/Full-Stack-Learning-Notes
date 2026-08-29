package org.shuai.sys.shiro.config;

import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.shuai.sys.shiro.filter.JwtAuthFilter;
import org.shuai.sys.shiro.realm.JwtRealm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    public static final String JWT_AUTH_FILTER = "jwtAuthFilter";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean
            (
                    @Qualifier("defaultWebSecurityManager") SecurityManager securityManager
            )
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 自定义 jwt filter
        Map<String, Filter> filtersMap = new LinkedHashMap<>(2);
        filtersMap.put(JWT_AUTH_FILTER,  new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 自定义 filterChain 路径过滤拦截规则
        Map<String, String> filterChainMap = new LinkedHashMap<>(16);
        // ==================== Knife4j / Swagger3 (OpenAPI 3) 放行 ====================
        filterChainMap.put("/doc.html", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/v3/api-docs/**", "anon");
        filterChainMap.put("/v3/api-docs", "anon");
        filterChainMap.put("/swagger-ui/**", "anon");
        filterChainMap.put("/swagger-ui.html", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/swagger-resources", "anon");
        filterChainMap.put("/favicon.ico", "anon");

        // ==================== 静态资源放行 ====================
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/images/**", "anon");
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/error/**", "anon");  // ← 关键：放行 /error 请求
        filterChainMap.put("/error", "anon");     // ← 关键：放行 /error 请求

        // ==================== 业务放行 ====================
        filterChainMap.put("/auth/**", "anon");

        // ==================== 其余所有请求走JWT认证 ====================
        filterChainMap.put("/**", JWT_AUTH_FILTER);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager defaultWebSecurityManager
            (
                    @Qualifier("jwtRealm") JwtRealm jwtRealm,
                    @Qualifier("shiroPermissionRedisCacheManager") CacheManager shiroPermissionRedisCacheManager
            )
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(jwtRealm);
        // 设置权限缓存，避免每次校验权限字符时，频繁查询数据库
        defaultWebSecurityManager.setCacheManager(shiroPermissionRedisCacheManager);
        // 禁用session，当前shiro没有session的存储用户认证信息，目前是基于jwt
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);
        return defaultWebSecurityManager;
    }

    /**
     * Shiro 生命周期处理器
     * 必须声明为 static，确保在其他 BeanPostProcessor 之前初始化
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

        /**
         * Shiro 授权注解切面，拦截 @RequiresRoles、@RequiresPermissions 等
         * 注意：需要配合 spring-boot-starter-aop 使用（由其提供 Advisor 自动代理）
         */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
            (
                    @Qualifier("defaultWebSecurityManager") SecurityManager securityManager
            )
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
