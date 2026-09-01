package com.it;

import com.it.mapper.Impl.UserMapperImpl;
import com.it.mapper.UserMapper;
import com.it.pojo.User;
import com.it.service.Impl.UserServiceImpl;
import com.it.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SSM_Test {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("-------------------------------------");
        UserService userService = context.getBean(UserService.class);
        userService.getUserList().forEach(System.out::println);
    }
}
