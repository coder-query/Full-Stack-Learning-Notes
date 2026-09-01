package com.mayikt.entity;

/**
 * @ClassName UserEntity
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class UserEntity {
    private String userName;
    private int age;

    public UserEntity() {

    }

    public UserEntity(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    //    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof UserEntity)
            return userName.equals(((UserEntity) obj).userName) && age == (((UserEntity) obj).age);
        else
            return false;
    }
}
