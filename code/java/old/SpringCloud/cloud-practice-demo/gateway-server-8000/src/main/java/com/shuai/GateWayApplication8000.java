package com.shuai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication8000 {
    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();
        ConfigurableApplicationContext springContextIOC = SpringApplication.run(GateWayApplication8000.class, args);
        long end = System.currentTimeMillis();
        System.out.println("启动耗时：" + (end - start));
    }
}
