package com.it;


import com.it.pojo.Dog;
import com.it.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocXMlTest {
    @Test
    public void Ioc_Xml_Test(){
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);
        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(user);
        System.out.println(dog);
    }
}
