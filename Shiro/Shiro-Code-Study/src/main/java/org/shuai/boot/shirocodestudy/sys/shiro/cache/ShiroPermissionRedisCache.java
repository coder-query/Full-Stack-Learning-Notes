package org.shuai.boot.shirocodestudy.sys.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ShiroPermissionRedisCache implements Cache<String, Object> {
    @Override
    public Object get(String s) throws CacheException {
        return null;
    }

    @Override
    public Object put(String s, Object o) throws CacheException {
        return null;
    }

    @Override
    public Object remove(String s) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<String> keys() {
        return Collections.emptySet();
    }

    @Override
    public Collection<Object> values() {
        return Collections.emptyList();
    }
}
