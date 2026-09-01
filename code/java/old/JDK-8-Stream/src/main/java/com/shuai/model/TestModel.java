package com.shuai.model;


public class TestModel {

    private Integer count;

    private String name;

    private Integer age;

    private String sex;

    public TestModel() {
    }

    public TestModel(Integer count, String name, Integer age, String sex) {
        this.count = count;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * 获取
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
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
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString() {
        return "TestModel{count = " + count + ", name = " + name + ", age = " + age + ", sex = " + sex + "}";
    }
}
