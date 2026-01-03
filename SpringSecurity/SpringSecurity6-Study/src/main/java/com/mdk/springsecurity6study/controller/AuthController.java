package com.mdk.springsecurity6study.controller;

import com.mdk.springsecurity6study.common.dto.LoginDTO;
import com.mdk.springsecurity6study.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;

    @GetMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录loginDTO: {}", loginDTO);
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPassword().trim();
        return jwtUtils.generateToken(username);
    }

}


