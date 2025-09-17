package com.it.controller;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/24 星期五 20:31
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getAll")
    public String getUser() {
        List<User> all = userMapper.getAll();
        for (User user : all) {
            System.out.println(user);
        }
        return all.toString();
    }

}

