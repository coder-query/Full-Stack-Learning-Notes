package com.it.ProxyTest;

import com.it.proxy.DynamicProxy;
import com.it.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: 心动的offer->17k
 * @Author 帅宏编码-coding
 * @Date 2025/1/19 星期日 20:45
 */
public class test_DynamicProxy {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userServiceImpl");


        //动态代理测试
        IUserService userProxyInstance = (IUserService) DynamicProxy.getProxyInstance(userService);
        System.out.println(userProxyInstance.getClass());
        System.out.println("方法执行结果为 : " + userProxyInstance.getUser());
        System.out.println("----------------------------------------------------");
    }

}
