package com.zsh;

import com.importSelector_test.zshConfig;
import com.it.config.ItBeansConfig;
import com.other.config.OtherBeansConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 23:44
 */
public class C_Spring_ImportSelector_Test {
	public static void main(String[] args) {
		System.out.println(OtherBeansConfig.class.getName());
		System.out.println(ItBeansConfig.class.getName());

		ApplicationContext IOC = new AnnotationConfigApplicationContext(zshConfig.class);

		for (String beanDefinitionName : IOC.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}

	}
}
