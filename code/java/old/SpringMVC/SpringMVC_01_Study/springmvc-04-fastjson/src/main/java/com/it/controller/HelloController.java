package com.it.controller;

import com.alibaba.fastjson.JSON;
import com.it.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//这个注解是不会走视图解析器,直接返回json数据给前端
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String json(){

        List<User> userList = new ArrayList<>();
        userList.add(new User("张三", 18, "男"));
        userList.add(new User("李四", 19, "女"));
        userList.add(new User("王五", 20, "男"));
        userList.add(new User("赵六", 21, "女"));
        String jsonString = JSON.toJSONString(userList);
        return jsonString;
    }

}
