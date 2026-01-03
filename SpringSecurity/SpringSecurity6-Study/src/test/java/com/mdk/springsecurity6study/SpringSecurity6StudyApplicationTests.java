package com.mdk.springsecurity6study;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurity6StudyApplicationTests {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
//        String encode = bCryptPasswordEncoder.encode("123456");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        // 检查 123456 是否匹配数据库中的密码
//        boolean matches = encoder.matches("123456", "$2a$10$Z8oNwlEhFRPD7fhOM88Sa.L/hZgOhbmmGV1HCfca6D7nEWTuNrwQ6");
//        System.out.println(matches);  // 如果是 false，说明密码不对
//        System.out.println(encode);
//

        String pwd = "Asd123456";
        String encodedPwd = bCryptPasswordEncoder.encode(pwd);
        System.out.println("encodedPwd = " + encodedPwd);
        boolean flah = bCryptPasswordEncoder.matches(pwd, "$2a$10$TQ..MPSA0zjnDN7Q336tDuLIzTwxzGL1EcWZOanUyuLdxhGKVzYMy");
        System.out.println("flah = " + flah);


        // $2a$10$AUPPC5omyTGpcgob0gIkzOIjoBf1V7FJ66pIHVhDRs3LGl4emmqFa

    }

}
