package org.shuai.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.Stream;

public class Spring_Two_Main {
    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application-context.xml");
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames)
                .filter(s-> !s.contains("springframework"))
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------");
        Stream.of(beanDefinitionNames)
                .forEach(System.out::println);
    }
}