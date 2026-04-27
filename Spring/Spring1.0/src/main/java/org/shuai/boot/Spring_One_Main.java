package org.shuai.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Spring_One_Main {
    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application-context.xml");
        System.out.println("ioc.getBean(\"userService\") = " + ioc.getBean("userService"));
    }
}