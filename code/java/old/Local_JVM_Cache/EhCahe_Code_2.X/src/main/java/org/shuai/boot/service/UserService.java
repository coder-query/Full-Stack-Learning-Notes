package org.shuai.boot.service;


import org.shuai.boot.model.User;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Resource
    private CacheManager ehCacheCacheManager;

    @Resource
    private CacheManager redisCacheManager;

    /**
     * 本地缓存：使用 EhCache
     */
    @Cacheable( cacheManager = "ehCacheCacheManager", key = "#username")
    public User getUserByUsername(String username) {
        System.out.println(ehCacheCacheManager);
        System.out.println("getUserByUsername 未命中本地缓存，查询数据库ing。。。");
        return User.builder()
                .username(username)
                .build();
    }

    /**
     * 分布式缓存：使用 Redis
     */
    @Cacheable(cacheManager = "redisCacheManager", key = "#username")
    public User getUserByUsernameFromRedis(String username) {
        System.out.println(redisCacheManager);
        System.out.println("getUserByUsernameFromRedis 未命中Redis缓存，查询数据库ing。。。");
        return User.builder()
                .username(username)
                .build();
    }
}
