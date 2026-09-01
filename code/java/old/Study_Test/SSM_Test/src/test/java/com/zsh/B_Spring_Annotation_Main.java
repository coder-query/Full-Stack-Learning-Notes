package com.zsh;

import com.it.config.ItBeansConfig;
import com.it.service.Impl.ItHelloServiceImpl;
import com.other.service.Impl.OtherHelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 11:43
 */
public class B_Spring_Annotation_Main {
	public static void main(String[] args) {

//		ApplicationContext IOC_01 = new AnnotationConfigApplicationContext(ItBeansConfig.class);
//
//		ItHelloServiceImpl beanItHelloService = IOC_01.getBean(ItHelloServiceImpl.class);
//		System.out.println(beanItHelloService);
//
//		OtherHelloServiceImpl beanOtherHelloService = IOC_01.getBean(OtherHelloServiceImpl.class);
//		System.out.println(beanOtherHelloService);


//		ApplicationContext IOC_02 = new AnnotationConfigApplicationContext(OtherBeansConfig.class);


//		String[] beanDefinitionNames_01 = IOC_01.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames_01) {
//			System.out.println(beanDefinitionName);
//		}

		System.out.println("-------------------------------------------------------------------------------");

//		String[] beanDefinitionNames_02 = IOC_02.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames_02) {
//			System.out.println(beanDefinitionName);
//		}

	}
}
