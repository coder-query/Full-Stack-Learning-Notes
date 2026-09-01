package com.shuai;

import com.shuai.api.service.SayHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@RestController
@SpringBootApplication
public class Main_Consumer_8000 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Consumer_8000.class, args);
    }

    @DubboReference
    private SayHelloService sayHelloService;

    @GetMapping("say/hello")
    public String say() {
        System.out.println("我是Consumer~");
        return sayHelloService.sayHello();
    }
}
