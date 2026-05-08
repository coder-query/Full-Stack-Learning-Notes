package com.shuai.springboot3demo;

import lombok.extern.slf4j.Slf4j;
import org.shaui.encrypt.bean.AesEncryptTemplate;
import org.shaui.encrypt.bean.RsaEncryptTemplate;
import org.shaui.encrypt.bean.SignEncryptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Springboot3DemoApplication implements CommandLineRunner {

    @Autowired
    private AesEncryptTemplate aesEncryptTemplate;

    @Autowired
    private SignEncryptTemplate signEncryptTemplate;

    @Autowired
    private RsaEncryptTemplate rsaEncryptTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Springboot3DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /**
         * sha256
         */
        String data = "123456";
        String s = signEncryptTemplate.encryptWithSha256(data);
        System.out.println(s);
    }
}
