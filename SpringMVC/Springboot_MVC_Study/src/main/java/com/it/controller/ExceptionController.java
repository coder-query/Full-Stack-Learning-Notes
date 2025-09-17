package com.it.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/artException")
    public String throwArtException() {
        int i = 1 / 0;
        return "我是throwArtException()";
    }


    @GetMapping("/nullException")
    public String throwNullException() {
        String str = null;
        System.out.println(str.toString());
        return "我是throwNullException()";
    }
}
