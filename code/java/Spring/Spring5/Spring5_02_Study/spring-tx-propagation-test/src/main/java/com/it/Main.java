package com.it;

import com.it.service.TxAService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 13:08
 */
public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext IOC =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		TxAService txAService = IOC.getBean(TxAService.class);
		txAService.updateStudentAgeById(1);
	}
}