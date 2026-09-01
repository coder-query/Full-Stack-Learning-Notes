package com.it;

import com.it.config.BeanConfig;
import com.it.pojo.Dog;
import com.it.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIocAnnotationTest {
    @Test
    public void Ioc_Annotation_Test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        User user = context.getBean("user", User.class);
        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(user);
        System.out.println(dog);
    }
}
