package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class SpringBoorTestApplication {
	public static void main(String[] args) {
		ApplicationContext IOC = SpringApplication.run(SpringBoorTestApplication.class, args);
	}
}
