package com.it;

import com.it.service.Impl.UserMapperImpl;
import com.it.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserMapperTest {
    @Autowired
    private SqlSession sqlSession;
    @Test
    public void test(){
       ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserMapperImpl userMapper = (UserMapperImpl) context.getBean("userMapper");
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

    }
}
