package com.it.controller;


import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    public String getUserList(Model model){
        System.out.println("UserController执行了....");

        model.addAttribute("userList",userService.getUserList());

        return "test";
    }
}
