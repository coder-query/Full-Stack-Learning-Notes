package com.shuai.boot;

import org.junit.jupiter.api.Test;
import org.shuai.boot.EhCache2Application;
import org.shuai.boot.model.User;
import org.shuai.boot.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = EhCache2Application.class)
public class TestLocalEhCache {

    @Resource
    private UserService userService;

    @Test
    public void testLocalEhCacheToDisk() {
        User shuai1 = userService.getUserByUsername("shuai");
        User shuai2 = userService.getUserByUsername("shuai");
        System.out.println(shuai1);
        System.out.println(shuai2);
    }
    @Test
    public void testRedisCache() {
        User shuai = userService.getUserByUsernameFromRedis("shuai");
        System.out.println(shuai);
        User shuai2 = userService.getUserByUsernameFromRedis("shuai");
        System.out.println(shuai2);
    }
}
