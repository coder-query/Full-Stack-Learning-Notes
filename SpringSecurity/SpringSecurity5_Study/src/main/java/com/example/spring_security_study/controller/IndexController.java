package com.example.spring_security_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/12 0012
 */
//@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:login.html";
    }
}
