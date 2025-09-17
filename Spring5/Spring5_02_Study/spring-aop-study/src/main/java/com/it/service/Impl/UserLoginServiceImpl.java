package com.it.service.Impl;

import com.it.service.UserLoginService;
import org.springframework.stereotype.Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/8 星期二 22:51
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
	@Override
	public void login(String username, String password) {
		if (username.equals("admin") && password.equals("123456")) {
			System.out.println("登录成功...!");
		} else {
			System.out.println("登录失败...!");
		}
	}
}
