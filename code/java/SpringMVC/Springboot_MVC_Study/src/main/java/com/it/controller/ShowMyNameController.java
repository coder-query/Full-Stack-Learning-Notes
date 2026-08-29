package com.it.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowMyNameController {

    @GetMapping("/name")
    public String showName() {
        return "这是HelloWorld的请求响应界面";
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable("UserId") Integer userId) {
        return "success";
    }

}
