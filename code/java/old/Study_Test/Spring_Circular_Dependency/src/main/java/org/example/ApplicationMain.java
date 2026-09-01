package org.example;

import org.example.config.BeanConfig;
import org.example.service.A;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ApplicationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IOC = new AnnotationConfigApplicationContext(BeanConfig.class);
        A a = IOC.getBean(A.class);
        System.out.println(a);
        System.out.println(a.getB());}
}