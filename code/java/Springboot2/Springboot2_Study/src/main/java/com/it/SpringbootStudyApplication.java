package com.it;

import com.it.service.HelloWorldService;
import org.shaui.encrypt.bean.AesEncryptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@ImportResource(value = "classpath:application-context.xml")
public class SpringbootStudyApplication implements CommandLineRunner {

    @Autowired
    private AesEncryptTemplate aesEncryptTemplate;

    public static void main(String[] args) {
        // 启动Spring Boot
        ConfigurableApplicationContext ioc = SpringApplication.run(SpringbootStudyApplication.class, args); // Spring3.0
        HelloWorldService bean = ioc.getBean(HelloWorldService.class);
        System.out.println(bean);
    }

    @Override
    public void run(String... args) throws Exception {
        String hex = aesEncryptTemplate.encryptWithAES_CBC("123");
        System.out.println(hex);
    }

}