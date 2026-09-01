package org.shuai.sys.shiro.cache.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ShiroPermissionRedisCacheManager implements CacheManager {

    @Resource
    private Cache shiroPermissionRedisCache;

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return shiroPermissionRedisCache;
    }
}
