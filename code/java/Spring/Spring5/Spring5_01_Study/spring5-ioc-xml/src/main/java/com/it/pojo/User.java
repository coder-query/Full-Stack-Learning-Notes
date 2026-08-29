package com.it.pojo;


public class User {
    private String username;
    private Dog dog;

    public User() {
        System.out.println("User无参构造");
    }

    public User(String username, Dog dog) {
        System.out.println("User有参构造");
        this.username = username;
        this.dog = dog;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        System.out.println("user -- setUsername()方法调用...");
        this.username = username;
    }

    /**
     * 获取
     * @return dog
     */
    public Dog getDog() {
        return dog;
    }

    /**
     * 设置
     * @param dog
     */
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String toString() {
        return "User{username = " + username + ", dog = " + dog + "}";
    }
}
