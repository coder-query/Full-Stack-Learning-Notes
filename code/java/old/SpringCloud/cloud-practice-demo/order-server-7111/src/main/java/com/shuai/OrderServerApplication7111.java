package com.shuai;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OrderServerApplication7111 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ConfigurableApplicationContext springContextIOC = SpringApplication.run(OrderServerApplication7111.class, args);
        long end = System.currentTimeMillis();
        System.out.println("启动耗时：" + (end - start));
    }

}
