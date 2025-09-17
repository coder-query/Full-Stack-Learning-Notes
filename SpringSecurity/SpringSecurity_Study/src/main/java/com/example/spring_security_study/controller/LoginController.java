package com.example.spring_security_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/11 0011
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
