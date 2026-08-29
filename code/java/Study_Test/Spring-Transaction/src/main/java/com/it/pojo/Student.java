package com.it.pojo;

import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding @Money java_offer_13k
 * @date 2025/4/9 星期三 1:14
 */
@Component
public class Student {
  private Integer stuId;
  private String stuName;
  private String gender;
  private Integer age;
  private String clazz;

  public Student() {}

  public Student(Integer stuId, String stuName, String gender, Integer age, String clazz) {
    this.stuId = stuId;
    this.stuName = stuName;
    this.gender = gender;
    this.age = age;
    this.clazz = clazz;
  }

  /**
   * 获取
   *
   * @return stuId
   */
  public Integer getStuId() {
    return stuId;
  }

  /**
   * 设置
   *
   * @param stuId
   */
  public void setStuId(Integer stuId) {
    this.stuId = stuId;
  }

  /**
   * 获取
   *
   * @return stuName
   */
  public String getStuName() {
    return stuName;
  }

  /**
   * 设置
   *
   * @param stuName
   */
  public void setStuName(String stuName) {
    this.stuName = stuName;
  }

  /**
   * 获取
   *
   * @return gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * 设置
   *
   * @param gender
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * 获取
   *
   * @return age
   */
  public Integer getAge() {
    return age;
  }

  /**
   * 设置
   *
   * @param age
   */
  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   * 获取
   *
   * @return clazz
   */
  public String getClazz() {
    return clazz;
  }

  /**
   * 设置
   *
   * @param clazz
   */
  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public String toString() {
    return "Student{stuId = "
        + stuId
        + ", stuName = "
        + stuName
        + ", gender = "
        + gender
        + ", age = "
        + age
        + ", clazz = "
        + clazz
        + "}";
  }
}
