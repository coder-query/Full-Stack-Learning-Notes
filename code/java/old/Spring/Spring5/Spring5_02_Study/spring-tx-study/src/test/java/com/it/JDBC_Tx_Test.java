package com.it;

import com.it.config.JavaConfig;
import com.it.pojo.Student;
import com.it.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 0:53
 */

public class JDBC_Tx_Test {

	private static AnnotationConfigApplicationContext IOC;

	static {
		IOC = new AnnotationConfigApplicationContext(JavaConfig.class);

	}

	@Test
	@Transactional
	public void testTx() {
		StudentService studentService = (StudentService) IOC.getBean("studentService");
		for (Student student : studentService.getAllStudents()) {
			System.out.println(student);
		}
	}

}
