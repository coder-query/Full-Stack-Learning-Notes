package com.it.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerPort8808 {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/getPort")
    public String getPort() {
        String formatRes = "端口号：%s";
        return String.format(formatRes, port);
    }
}
