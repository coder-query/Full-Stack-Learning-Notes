package com.it.service.Impl;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        System.out.println("UserMapper拿到了....");
        return userMapper.getUserList();
    }
}
