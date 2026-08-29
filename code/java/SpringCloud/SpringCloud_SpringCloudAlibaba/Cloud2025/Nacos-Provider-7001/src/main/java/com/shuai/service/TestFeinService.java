package com.shuai.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TestFeinService {
    @RequestMapping("/getMessage")
    String getMessage(@RequestParam(value = "name") String name);
}
