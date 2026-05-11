package com.provider.service;

import com.api.service.ILoginService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/5 0005
 */
@DubboService
public class LoginServiceImpl implements ILoginService {
    @Override
    public String login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return "success";
        }
        return "fail";
    }
}
