package com.mdk.springsecurity6study.controller;

import com.mdk.springsecurity6study.common.dto.LoginDTO;
import com.mdk.springsecurity6study.service.api.auth.AuthService;
import com.mdk.springsecurity6study.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证接口", description = "认证接口")
public class AuthController {

    private final AuthService AuthService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public String login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录loginDTO: {}", loginDTO);
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPassword().trim();
        return AuthService.login(username, password);
    }

}


