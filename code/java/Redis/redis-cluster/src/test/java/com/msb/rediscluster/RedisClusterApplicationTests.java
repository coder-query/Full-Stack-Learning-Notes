package com.msb.rediscluster;

import com.msb.rediscluster.access.RedisString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisClusterApplicationTests {

    @Autowired
    private RedisString redisString;

    @Test
    void contextLoads() {
        String set = redisString.set("test", "123");
        System.out.println(set);
        String get = redisString.get("test");
        System.out.println(get);
    }

}
