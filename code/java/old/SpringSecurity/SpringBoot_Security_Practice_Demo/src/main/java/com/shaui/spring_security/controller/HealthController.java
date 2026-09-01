package com.shaui.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/13 0013
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String testHealth() {
        return "Springboot 工程 集成 SpringSecurity 启动完成~~~";
    }

}
