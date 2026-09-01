package com.shuai.service.impl;

import com.shuai.service.TestFeinService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeinServiceImpl implements TestFeinService {
    @Override
    public String getMessage(@RequestParam(value = "name") String name) {
        return name + "，欢迎来到 Nacos-Provider-7001 ！";
    }
}
