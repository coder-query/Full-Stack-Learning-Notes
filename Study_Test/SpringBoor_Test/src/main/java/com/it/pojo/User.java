package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 11:18
 */

//@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private Integer age;
	private String name;
	private String gender;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", age=" + age +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				'}';
	}
}
