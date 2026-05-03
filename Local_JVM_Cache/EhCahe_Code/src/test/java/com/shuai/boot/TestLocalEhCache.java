package com.shuai.boot;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.xml.model.MemoryUnit;
import org.junit.jupiter.api.Test;
import org.shuai.boot.EhCacheApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EhCacheApplication.class)
public class TestLocalEhCache {
    @Test
    public void testLocalEhCache() {
        // 1. 创建 CacheManager
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(
                        "test-cache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                Object.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(20).build()
                        )
                ).build();
        // 2. 启动 CacheManager
        cacheManager.init();

        // 3. 获取 Cache
        Cache<String, Object> testCache = cacheManager.getCache("test-cache", String.class, Object.class);

        for (int i = 1; i <= 17; i++){
            testCache.put("key" + i, "value" + i);
        }

        for (int i = 1; i <= 17; i++){
            System.out.println(testCache.get("key" + i));
        }
    }
}
