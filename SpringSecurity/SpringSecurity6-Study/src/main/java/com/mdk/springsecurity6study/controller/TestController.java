package com.mdk.springsecurity6study.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {


    @GetMapping("/test")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public String test() {
        return "test";
    }
}
