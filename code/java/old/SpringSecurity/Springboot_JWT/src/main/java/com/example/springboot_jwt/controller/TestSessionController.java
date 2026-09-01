package com.example.springboot_jwt.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/11 0011
 */
@RestController
public class TestSessionController {

    @GetMapping("/test/session")
    public String generateSession(String username, HttpServletRequest request) {
        request.getSession().setAttribute("username", username);
        return request.getSession().toString();
    }
}
