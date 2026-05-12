package org.shuai.sys.shiro.cache.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ShiroPermissionRedisCache<K, V> implements Cache<K, V>{

    @Resource
    private JedisPool jedisPool;

    public static final String SHIRO_PERMISSION_CACHE_KEY = "shiro_permission_cache_key:user_id:";

    // 24 小时
    public static final long SHIRO_PERMISSION_CACHE_EXPIRE_TIME = 60 * 60 * 24L;

    /** 存储JSON中标记值类型的Key */
    private static final String CLASS_KEY = "@class";
    /** 存储JSON中标记值数据的Key */
    private static final String DATA_KEY = "@data";

    public static String buildShiroPermissionCacheKey(Integer userId){
        return SHIRO_PERMISSION_CACHE_KEY + userId;
    }

    public static Integer getUserIdFromKey(String jsonKey){
        JSONObject jsonObject = JSON.parseObject(jsonKey);
        if (jsonObject != null){
            String jsonObjectString = jsonObject.getString("primaryPrincipal");
            if (StrUtil.isNotBlank(jsonObjectString)){
                JSONObject jsonObj = JSON.parseObject(jsonObjectString);
                if (jsonObj != null){
                    return jsonObj.getInteger("id");
                }
            }
        }
        return -1;
    }

    /**
     * 序列化值：携带类型信息，便于反序列化时还原为正确的类型
     */
    private String serializeValue(V v) {
        JSONObject wrapper = new JSONObject();
        wrapper.put(CLASS_KEY, v.getClass().getName());
        wrapper.put(DATA_KEY, JSON.toJSON(v));
        return wrapper.toJSONString();
    }

    /**
     * 反序列化值：根据存储的类型信息还原为正确的Java对象
     * 兼容旧格式（无@class包装的纯JSON）
     */
    @SuppressWarnings("unchecked")
    private V deserializeValue(String json) {
        try {
            JSONObject wrapper = JSON.parseObject(json);
            if (wrapper == null) {
                return null;
            }
            if (wrapper.containsKey(CLASS_KEY)) {
                // 新格式：带@class类型信息
                String className = wrapper.getString(CLASS_KEY);
                Class<?> clazz = Class.forName(className);
                return (V) JSON.parseObject(wrapper.getString(DATA_KEY), clazz);
            }
            // 旧格式兼容：手动从JSON构建SimpleAuthorizationInfo
            if (wrapper.containsKey("roles") || wrapper.containsKey("stringPermissions")) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                if (wrapper.containsKey("roles")) {
                    Set<String> roles = new HashSet<>(wrapper.getJSONArray("roles").toJavaList(String.class));
                    info.setRoles(CollUtil.newHashSet(roles));
                }
                if (wrapper.containsKey("stringPermissions")) {
                    Set<String> perms = new HashSet<>(wrapper.getJSONArray("stringPermissions").toJavaList(String.class));
                    info.setStringPermissions(CollUtil.newHashSet(perms));
                }
                return (V) info;
            }
        } catch (Exception e) {
            // 反序列化失败
            log.error("ShiroPermissionRedisCache");
        }
        return null;
    }

    @Override
    public V get(K k) throws CacheException {
        try(Jedis jedis = jedisPool.getResource()){
            Integer userIdFromKey = getUserIdFromKey(JSON.toJSONString(k));
            if (userIdFromKey == -1){
                log.error("ShiroPermissionRedisCache put 获取用户id失败");
                throw new RuntimeException("ShiroPermissionRedisCache put 获取用户id失败");
            }
            String cacheKey = ShiroPermissionRedisCache.buildShiroPermissionCacheKey(userIdFromKey);
            String value = jedis.get(cacheKey);
            System.out.println("ShiroPermissionRedisCache redis查询权限");
            log.info("ShiroPermissionRedisCache redis查询权限。key = {},value = {}",cacheKey, value);
            if (StrUtil.isNotBlank(value)){
                jedis.expire(cacheKey, SHIRO_PERMISSION_CACHE_EXPIRE_TIME);
                return deserializeValue(value);
            }
            return null;
        }
    }

    @Override
    public V put(K k, V v) throws CacheException {
        try(Jedis jedis = jedisPool.getResource()){
            Integer userIdFromKey = getUserIdFromKey(JSON.toJSONString(k));
            if (userIdFromKey == -1){
                log.error("ShiroPermissionRedisCache put 获取用户id失败");
                throw new RuntimeException("ShiroPermissionRedisCache put 获取用户id失败");
            }
            String cacheKey = ShiroPermissionRedisCache.buildShiroPermissionCacheKey(userIdFromKey);
            SetParams setParams = new SetParams()
                            .ex(SHIRO_PERMISSION_CACHE_EXPIRE_TIME);
            jedis.set(cacheKey, serializeValue(v), setParams);
            System.out.println("ShiroPermissionRedisCache redis设置权限");
            log.info("ShiroPermissionRedisCache redis设置权限。 key = {},value = {}",cacheKey, serializeValue(v));
            return v;
        }
    }

    @Override
    public V remove(K k) throws CacheException {
        try(Jedis jedis = jedisPool.getResource()){
            Integer userIdFromKey = getUserIdFromKey(JSON.toJSONString(k));
            if (userIdFromKey == -1){
                log.error("ShiroPermissionRedisCache put 获取用户id失败");
                throw new RuntimeException("ShiroPermissionRedisCache put 获取用户id失败");
            }
            String cacheKey = ShiroPermissionRedisCache.buildShiroPermissionCacheKey(userIdFromKey);
            String value = jedis.get(cacheKey);
            if (StrUtil.isNotBlank(value)){
                jedis.del(cacheKey);
                System.out.println("ShiroPermissionRedisCache redis移除权限");
                log.info("ShiroPermissionRedisCache redis移除权限。 key = {},value = {}",cacheKey, value);
                return deserializeValue(value);
            }
            return null;
        }
    }

    @Override
    public void clear() throws CacheException {
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> keys = jedis.keys(SHIRO_PERMISSION_CACHE_KEY + "*");
            Pipeline pipelined = jedis.pipelined();
            keys.stream().filter(StrUtil::isNotBlank).forEach(pipelined::del);
            pipelined.sync();
        }
    }

    @Override
    public int size() {
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> keys = jedis.keys(SHIRO_PERMISSION_CACHE_KEY + "*");
            return keys.size();
        }
    }

    @Override
    public Set<K> keys() {
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> keys = jedis.keys(SHIRO_PERMISSION_CACHE_KEY + "*");
            return keys.stream().map(k -> (K) k).collect(Collectors.toSet());
        }
    }

    @Override
    public Collection<V> values() {
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> keys = jedis.keys(SHIRO_PERMISSION_CACHE_KEY + "*");
            Pipeline pipelined = jedis.pipelined();
            keys.stream().filter(StrUtil::isNotBlank).forEach(pipelined::get);
            List<Object> values = pipelined.syncAndReturnAll();
            return (Collection<V>) values;
        }
    }
}
