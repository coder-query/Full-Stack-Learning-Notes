package com.shuai.springboot3demo.controller;

import com.shuai.springboot3demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Resource
    private UserService userService;

    @GetMapping("/demo")
    public String test() {
        return userService.test();
    }

}