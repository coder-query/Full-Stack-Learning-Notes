package org.shuai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
public class TestJedis {

    @Resource
    private JedisPool jedisPool;

    /**
     * 注入的时候有俩种
     * 根据类的类型注入
     * 根据名称注入
     */
    @Autowired// 注入redisTemplate。可以直接使用redisTemplate进行操作
//    @Qualifier(value = "redisTemplate")
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testJedis() {
        try(Jedis jedis = jedisPool.getResource()){
            String info = jedis.info();
            System.out.println(info);
        }
    }
    @Test
    public void testRedisTemplate() {
        // oop
        redisTemplate.opsForValue().set("name666", "我是java工程师");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

}
