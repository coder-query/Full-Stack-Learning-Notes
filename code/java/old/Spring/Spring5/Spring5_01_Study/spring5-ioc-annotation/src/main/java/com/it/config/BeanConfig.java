package com.it.config;

import com.it.pojo.Dog;
import com.it.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private Dog dog;

    @Bean
    public User user() {
        return new User("小张", dog);
    }

    @Bean
    public Dog dog() {
        return new Dog("小狗狗","灰色");
    }

}
