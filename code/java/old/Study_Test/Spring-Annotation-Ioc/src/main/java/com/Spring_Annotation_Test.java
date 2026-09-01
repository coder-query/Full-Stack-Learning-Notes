package com;

import com.importSelector_Test.My_ImportSelector_Configuration;
import com.it.config.ItBeansConfiguration;
import com.other.config.OtherBeansConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 10:03
 */
public class Spring_Annotation_Test {
	public static void main(String[] args) {

//		ApplicationContext IOC =
//				new AnnotationConfigApplicationContext(ItBeansConfiguration.class);
//
//		for (String beanDefinitionName : IOC.getBeanDefinitionNames()) {
//			System.out.println(beanDefinitionName);
//		}
//
//		System.out.println("-------------------------------------------------------------------");

		ApplicationContext IOC =
				new AnnotationConfigApplicationContext(My_ImportSelector_Configuration.class);


		System.out.println(ItBeansConfiguration.class.getName());
		System.out.println(OtherBeansConfiguration.class.getName());
		System.out.println("=======================================================================");

		for (String beanDefinitionName : IOC.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}

	}
}
