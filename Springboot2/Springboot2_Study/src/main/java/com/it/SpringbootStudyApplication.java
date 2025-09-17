package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootStudyApplication {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        SpringApplication.run(SpringbootStudyApplication.class, args);
    }

}
