package com.shuai.cloud.provider.controller;

import com.commons.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/6 0006
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("test/loadBalance")
    public String testLoadBalance(@Value("${consul.config.info}") String consulConfigInfo) {
        try {
            Thread.sleep(6000 * 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("consulConfigInfo: " + consulConfigInfo + "" + "port: " + port);
        return "consulConfigInfo: " + consulConfigInfo + "" + "port: " + port;
    }
}
