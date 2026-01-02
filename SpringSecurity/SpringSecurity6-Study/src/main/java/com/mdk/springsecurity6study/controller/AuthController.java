package com.mdk.springsecurity6study.controller;

import com.mdk.springsecurity6study.service.api.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SysUserService sysUserService;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("用户名: {}, 密码: {}", username, password);
        return sysUserService.login(username, password);
    }
}
