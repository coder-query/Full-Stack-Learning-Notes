package com.consumer;

import com.api.service.ILoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/6 0006
 */
public class Main_8000 {
    public static void main(String[] args) {

        ILoginService iLoginService = null;

        ApplicationContext IOC =
                new ClassPathXmlApplicationContext("classpath:META-INF/spring/application.xml");
        iLoginService = IOC.getBean(ILoginService.class);
        System.out.println(iLoginService.login("admin", "admin"));
    }


}