package com.it.ProxyTest;

import com.it.proxy.StaticProxyUserServiceImpl;
import com.it.service.IUserService;
import com.it.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: 心动的offer->17k
 * @Author 帅宏编码-coding
 * @Date 2025/1/19 星期日 20:44
 */
public class test_StaticProxy {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userServiceImpl");

        //静态代理测试
        // 一个静态代理类，用于为 UserServiceImpl 提供代理服务
        StaticProxyUserServiceImpl proxy = new StaticProxyUserServiceImpl(userService);
        System.out.println(proxy.getClass());
        System.out.println("方法执行结果为 : " +proxy.getUser());
        System.out.println("----------------------------------------------------");

    }
}
