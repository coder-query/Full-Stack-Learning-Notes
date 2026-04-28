package org.shuai.boot;

import org.shuai.boot.config.BootApplicationContextConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.Stream;

@EnableAspectJAutoProxy
public class Spring_Four_Main {
    public static void main(String[] args) {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(BootApplicationContextConfig.class);
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames)
                .filter(s-> !s.contains("springframework"))
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------");
        Stream.of(beanDefinitionNames)
                .forEach(System.out::println);
    }
}