package com.it.ProxyTest;

import com.it.component.UserController;
import com.it.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: 心动的offer->17k
 * @Author 帅宏编码-coding
 * @Date 2025/1/19 星期日 20:46
 */
public class test_SpringProxy {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //如果被代理的类实现了某个接口,那么 spring aop 会自动选择jdk代理方式
        IUserService iUserService = (IUserService) context.getBean("userServiceImpl");
        System.out.println(iUserService.getClass());
        System.out.println("方法执行结果为 : " +iUserService.getUser());

        System.out.println("----------------------------------------------------");

        //如果被代理的类没有实现任何接口,那么 spring aop 会自动选择cjlib代理方式
        UserController userController = context.getBean(UserController.class);
        System.out.println(userController.getClass());
        System.out.println("方法执行结果为 : " +userController.getUser());
    }
}
