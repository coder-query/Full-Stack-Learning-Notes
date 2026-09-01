package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
@Data
public class Student {
	private Integer stuId;
	private String stuName;
	private Integer stuAge;
	private String stuClazz;

	public Student() {
	}

	public Student(String stuName, Integer stuAge, String stuClazz) {
		this.stuAge = stuAge;
		this.stuClazz = stuClazz;
		this.stuName = stuName;
	}

	public Student(Integer stuId, String stuName) {
		this.stuId = stuId;
		this.stuName = stuName;
	}
}
