package org.shuai.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfig {
    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate666(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 1. 构建Jackson序列化器（与缓存配置共用，避免序列化不一致）
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = buildJacksonSerializer();
        // 2. String序列化器（用于key）
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        // 3. 配置key和hashKey的序列化（String）
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // 4. 配置value和hashValue的序列化（Jackson JSON）
        redisTemplate.setValueSerializer(jacksonSerializer);
        redisTemplate.setHashValueSerializer(jacksonSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 构建Jackson JSON序列化器（通用工具方法）
     * 作用：统一Redis缓存中对象的序列化/反序列化规则，解决多场景下的序列化问题
     *
     * @return 配置完成的Jackson2JsonRedisSerializer实例
     */
    private Jackson2JsonRedisSerializer<Object> buildJacksonSerializer() {
        // 1. 创建ObjectMapper实例（Jackson的核心工具类，负责具体的序列化/反序列化逻辑）
        // ObjectMapper相当于JSON处理的"引擎"，所有序列化规则都通过它配置
        ObjectMapper objectMapper = new ObjectMapper();

        // 2. 配置1：反序列化时忽略未知字段
        // 场景：如果Redis中缓存的JSON数据包含了Java对象中没有的字段（如对象升级新增字段），
        // 不配置此选项会直接抛出"Unrecognized field"异常，配置后会自动忽略这些未知字段，避免报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 3. 配置2：设置对象字段的可见性规则
        // PropertyAccessor.ALL：表示对对象的所有属性（字段、getter/setter方法）生效
        // JsonAutoDetect.Visibility.ANY：表示所有可见性级别的字段都允许序列化/反序列化
        // 作用：解决私有字段（private修饰）无法被序列化的问题（默认Jackson只序列化public字段或有getter的字段）
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 4. 配置3：启用默认类型识别（最核心的配置，解决反序列化"类型丢失"问题）
        // 场景：如果直接序列化List<SysUser>、Set<String>等复杂类型，默认JSON会丢失原始类型信息，
        // 反序列化时会被识别为普通ArrayList/HashSet，无法还原为原类型；此配置会在JSON中嵌入类型信息
        objectMapper.activateDefaultTyping(
                // 4.1 类型验证器：LaissezFaireSubTypeValidator.instance（宽松的类型验证）
                // 作用：允许反序列化时匹配所有兼容的子类型，不做严格的类型校验（适合大多数业务场景）
                LaissezFaireSubTypeValidator.instance,
                // 4.2 类型包含规则：ObjectMapper.DefaultTyping.NON_FINAL
                // 含义：对所有非final修饰的类启用类型嵌入（final类如String、Integer本身类型明确，无需嵌入）
                // 比如SysUser（非final）会嵌入类型，String（final）不会嵌入
                ObjectMapper.DefaultTyping.NON_FINAL,
                // 4.3 类型信息的嵌入方式：JsonTypeInfo.As.PROPERTY
                // 含义：将类型信息以"@class"为key的属性，嵌入到JSON对象中
                // 示例：序列化SysUser后JSON会多一个字段："@class":"com.common.model.entity.SysUser"
                JsonTypeInfo.As.PROPERTY
        );

        // 注册 Java 8 时间模块（解决 LocalDateTime 报错）
        objectMapper.registerModule(new JavaTimeModule());

        // 5. 创建Jackson JSON序列化器实例，指定序列化的目标类型为Object（支持所有对象类型）
        // 并传入已配置好的ObjectMapper实例（替代已废弃的setObjectMapper方法）
        // Jackson2JsonRedisSerializer是Spring Data Redis提供的，适配Redis的Jackson序列化器
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 6. 返回配置完成的序列化器
        return serializer;
    }

}