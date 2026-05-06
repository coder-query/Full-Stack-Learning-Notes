package org.shuai.boot.shirocodestudy.sys.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.shuai.boot.shirocodestudy.sys.shiro.filter.JwtAuthFilter;
import org.shuai.boot.shirocodestudy.sys.shiro.realm.CustomRealm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    @Value("${spring.cache.type:ehcache}")
    private String cacheType;

    @Value("${shiro.session-timeout:7200}")
    private int shiroSessionTimeout;

    public static final String JWT_AUTH_FILTER = "jwtAuthFilter";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put(JWT_AUTH_FILTER,  new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        shiroFilterFactoryBean.setSecurityManager((DefaultWebSecurityManager)securityManager);
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/doc.html", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/v2/api-docs/**", "anon");
        filterChainMap.put("/auth/**", "anon");
//        filterChainMap.put("/**","authc");
        filterChainMap.put("/**", JWT_AUTH_FILTER);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm, @Qualifier("shiroPermissionRedisCacheManager") CacheManager shiroPermissionRedisCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        // 设置权限缓存，避免每次校验权限字符时，频繁查询数据库
        defaultWebSecurityManager.setCacheManager(shiroPermissionRedisCacheManager);
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

//    /**
//     * RedisSessionDAO shiro sessionDao层的实现 通过redis
//     * 使用的是shiro-redis开源插件
//     */
//
//
//    @Bean
//    public SessionDAO sessionDAO() {
//        if (CacheConstant.Redis_Type.equals(cacheType)) {
//            log.warn("当前Session存储类型为 cacheType = {}",cacheType);
//            return new RedisSessionDAO();
//        } else {
//            log.warn("当前Session存储类型为 cacheType = {}",cacheType);
//            return new MemorySessionDAO();
//        }
//    }
//
//
//    /**
//     * shiro session的管理
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager(SessionDAO sessionDAO) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setGlobalSessionTimeout(shiroSessionTimeout * 1000L);
////        sessionManager.setSessionDAO(sessionDAO);
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        listeners.add(new BDSessionListener());
//        sessionManager.setSessionListeners(listeners);
//        return sessionManager;
//    }

}
