package com.zsh;

import com.it.controller.ItHelloController;
import com.it.pojo.User;
import com.it.service.Impl.ItHelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 10:03
 */
public class A_Spring_Xml_Main {
	public static void main(String[] args) {

		ApplicationContext IOC = new ClassPathXmlApplicationContext("test-spring-ioc.xml");

//		ItHelloServiceImpl beanItHelloService = IOC.getBean(ItHelloServiceImpl.class);

		User beanUser = IOC.getBean(User.class);
		ItHelloController beanItHelloController = IOC.getBean(ItHelloController.class);
		ItHelloServiceImpl beanItHelloService = IOC.getBean(ItHelloServiceImpl.class);

		System.out.println(beanUser);
		System.out.println(beanItHelloController);
		System.out.println(beanItHelloService);

		System.out.println("-----------------------------------------------------------------");
		//System.out.println("User.class.getName()--->" + User.class.getName());

	}
}