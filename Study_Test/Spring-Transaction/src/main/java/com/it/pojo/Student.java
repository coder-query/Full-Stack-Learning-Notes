package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 1:14
 */
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Student {
	private Integer stuId;
	private String stuName;
	private String gender;
	private Integer age;
	private String clazz;
}
