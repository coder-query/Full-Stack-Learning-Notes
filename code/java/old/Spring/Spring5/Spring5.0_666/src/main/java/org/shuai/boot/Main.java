package org.shuai.boot;

import org.shuai.boot.config.BootConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ioc1 = new AnnotationConfigApplicationContext("org.shuai.boot.config");
        AnnotationConfigApplicationContext ioc2 = new AnnotationConfigApplicationContext(BootConfig.class);
        Stream.of(ioc1.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("------------------------");
        Stream.of(ioc2.getBeanDefinitionNames()).forEach(System.out::println);
        ioc2.stop();
    }
}