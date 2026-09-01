package com.shaui;

import com.shaui.spring_security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootSecurityPracticeDemoApplicationTests {

    @Resource
    private JwtUtils jwtUtils;

    @Test
    void testEncodeAndDeCodeJwt() {

        String token = jwtUtils.createToken(1L, "zsh");
        System.out.println("jwt -->" + token);
        System.out.println("------------------------------");
        Long userId = jwtUtils.getUserId(token);
        String username = jwtUtils.getUsername(token);
        System.out.println("userId ---> " + userId);
        System.out.println("userName ---> " + username);

    }

}
