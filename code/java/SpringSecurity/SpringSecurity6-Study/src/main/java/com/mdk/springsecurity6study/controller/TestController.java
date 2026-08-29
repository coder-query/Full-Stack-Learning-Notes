package com.mdk.springsecurity6study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试接口", description = "测试接口")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "测试接口")
    @PreAuthorize("hasRole('platform_admin')")
//    @PreAuthorize("hasAuthority('sys:user:list')")
    public String test() {
        return "test";
    }
    @GetMapping("/test2")
    @Operation(summary = "测试接口2")
    @PreAuthorize("hasRole('platform_admin')")
    public String test2() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
}
