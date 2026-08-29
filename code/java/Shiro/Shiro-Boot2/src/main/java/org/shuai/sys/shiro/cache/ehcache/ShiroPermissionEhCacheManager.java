package org.shuai.sys.shiro.cache.ehcache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ShiroPermissionEhCacheManager implements CacheManager {

    @Resource
    private Cache shiroPermissionEhCache;

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return shiroPermissionEhCache;
    }
}
