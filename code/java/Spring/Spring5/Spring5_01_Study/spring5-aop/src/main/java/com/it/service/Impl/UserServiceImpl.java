package com.it.service.Impl;

import com.it.pojo.User;
import com.it.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User getUser() {
        System.out.println("userServiceImpl -- getUserList()方法调用...");
        return new User("小张", 18, "男");
    }
}
