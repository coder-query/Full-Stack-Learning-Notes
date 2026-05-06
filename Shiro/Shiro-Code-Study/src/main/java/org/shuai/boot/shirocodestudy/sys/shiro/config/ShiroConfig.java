package org.shuai.boot.shirocodestudy.sys.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.shuai.boot.shirocodestudy.sys.constants.CacheConstant;
import org.shuai.boot.shirocodestudy.sys.shiro.filter.JwtAuthFilter;
import org.shuai.boot.shirocodestudy.sys.shiro.listener.BDSessionListener;
import org.shuai.boot.shirocodestudy.sys.shiro.realm.CustomRealm;
import org.shuai.boot.shirocodestudy.sys.shiro.session.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    public static final String JWT_AUTH_FILTER = "jwtAuthFilter";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 添加自定义的jwt过滤器
        Map<String, Filter> filtersMap = new LinkedHashMap<>(2);
        filtersMap.put(JWT_AUTH_FILTER,  new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置请求路径的认证
        Map<String, String> filterChainMap = new LinkedHashMap<>(8);
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
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 设置自定义账号密码认证和授权方式realm
        defaultWebSecurityManager.setRealm(customRealm);
        //
//        defaultWebSecurityManager.setCacheManager();
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


    /**
     * 下面是基于cookie + Session的配置 ，如果是jwt，则注释掉下面的配制即可
     */
//    /**
//     * RedisSessionDAO shiro sessionDao层的实现 通过redis
//     * 使用的是shiro-redis开源插件
//     */
//
//
//    @Bean
//    public SessionDAO sessionDAO(@Value("${spring.cache.type:ehcache}") String cacheType) {
//        if (CacheConstant.Redis_Type.equals(cacheType)) {
//            log.warn("当前Session存储类型为 cacheType = {}",cacheType);
//            return new RedisSessionDAO();
//        } else {
//            log.warn("当前Session存储类型为 cacheType = {}",cacheType);
//            return new MemorySessionDAO();
//        }
//    }

//
//    /**
//     * shiro session的管理
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager(SessionDAO sessionDAO,
//                                                   @Value("${shiro.session-timeout:7200}") int shiroSessionTimeout) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setGlobalSessionTimeout(shiroSessionTimeout * 1000L);
////        sessionManager.setSessionDAO(sessionDAO);
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        listeners.add(new BDSessionListener());
//        sessionManager.setSessionListeners(listeners);
//        return sessionManager;
//    }

}
