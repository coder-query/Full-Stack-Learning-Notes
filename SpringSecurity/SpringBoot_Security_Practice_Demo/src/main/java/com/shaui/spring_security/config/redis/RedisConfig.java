package com.shaui.spring_security.config.redis;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis cache configuration。
 */
@Configuration
@EnableCaching
@SuppressWarnings("all")
public class RedisConfig implements CachingConfigurer {

    /**
     * wirter RedisTemplate Bean。
     *
     * @param connectionFactory
     * @return template
     */
    @Bean
    public RedisTemplate<Object, ?> redisTemplate(final RedisConnectionFactory connectionFactory) {
        return createTemplate(connectionFactory);
    }

    /**
     * 创建redisTemplate.
     *
     * @param connectionFactory 连接工厂
     * @return redisTemplate
     */
    public static RedisTemplate<Object, ?> createTemplate(
            final RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2RedisSerializer serializer = new FastJson2RedisSerializer(Object.class);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.setDefaultSerializer(serializer);
        template.setStringSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

}

