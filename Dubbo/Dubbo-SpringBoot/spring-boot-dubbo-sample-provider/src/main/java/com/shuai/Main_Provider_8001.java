package com.shuai;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */

@EnableDubbo(scanBasePackages = "com.shuai.provider.service")
@SpringBootApplication
public class Main_Provider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Provider_8001.class, args);
    }
}
