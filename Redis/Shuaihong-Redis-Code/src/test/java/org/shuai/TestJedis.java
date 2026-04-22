package org.shuai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
public class TestJedis {

    @Resource
    private JedisPool jedisPool;

    @Test
    public void testJedis() {
        try(Jedis jedis = jedisPool.getResource()){
            String info = jedis.info();
            System.out.println(info);
        }
    }

}
