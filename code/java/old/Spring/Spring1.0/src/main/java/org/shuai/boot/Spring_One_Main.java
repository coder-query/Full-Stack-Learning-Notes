package org.shuai.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_One_Main {
    public static void main(String[] args) {
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("application-context.xml");
        System.out.println("ioc.getBeax`n(\"userService\") = " + ioc.getBean("userService"));
    }
}