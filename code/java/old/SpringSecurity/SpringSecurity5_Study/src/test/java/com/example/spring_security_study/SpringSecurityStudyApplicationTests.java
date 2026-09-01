package com.example.spring_security_study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
class SpringSecurityStudyApplicationTests {

    @Test
    void testEncryptionPassword() {
        // strength – the log rounds to use, between 4 and 31
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        String encodePassword = bCryptPasswordEncoder.encode("zsh");
        System.out.println("encodePassword ---> " + encodePassword);

        boolean flag = bCryptPasswordEncoder.matches("zsh", encodePassword);
        System.out.println("是否匹配 : " + flag);
    }

}
