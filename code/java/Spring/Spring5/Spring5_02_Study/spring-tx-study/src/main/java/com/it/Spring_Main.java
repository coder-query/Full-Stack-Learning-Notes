package com.it;

import com.it.config.JavaConfig;
import com.it.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 10:55
 */
public class Spring_Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext IOC =
				new AnnotationConfigApplicationContext(JavaConfig.class);
		StudentService studentService = (StudentService) IOC.getBean(StudentService.class);
		System.out.println(studentService);
		studentService.changeInfo();
	}
}
