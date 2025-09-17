package com.shuai.provider.service;

import com.shuai.api.service.SayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@Service
public class SayHelloImpl implements SayHelloService {
    @Override
    public String sayHello() {
        System.out.println("我是Provider~");
        return "嗨喽,靓仔!";
    }
}
