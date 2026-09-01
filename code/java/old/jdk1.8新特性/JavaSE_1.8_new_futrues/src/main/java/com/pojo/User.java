package com.pojo;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/15 星期二
 */
public class User {
	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * 获取
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 设置
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "User{name = " + name + ", age = " + age + "}";
	}
}
