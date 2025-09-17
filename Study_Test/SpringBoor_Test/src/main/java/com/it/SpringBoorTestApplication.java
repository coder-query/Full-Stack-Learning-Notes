package com.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("com.it.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching
public class SpringBoorTestApplication {
	public static void main(String[] args) {
		ApplicationContext IOC = SpringApplication.run(SpringBoorTestApplication.class, args);
	}
}
