package com.it;

import com.it.service.UserLoginService;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/8 星期二 22:40
 */

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext IOC =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserLoginService loginService = IOC.getBean(UserLoginService.class);
		loginService.login("admin", "123456");
	}
}