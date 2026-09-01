package org.example.boot_redis_template_code.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/** Redis配置 - 使用Fastjson2并加强安全防护 */
@Slf4j
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {

    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

    redisTemplate.setConnectionFactory(redisConnectionFactory);

    // 使用StringRedisSerializer来序列化和反序列化redis的key值
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    FastJsonRedisSerializer<Object> fastJson2JsonRedisSerializer =
        new FastJsonRedisSerializer<>(Object.class);
    // 设置序列化规则
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer);

    // 可选：根据需要决定是否启用事务支持
    // redisTemplate.setEnableTransactionSupport(true);

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
