package com.it.pojo;



public class Dog {
    private String name;
    private String color;

    public Dog() {
        System.out.println("dog 无参构造...");
    }

    public Dog(String name, String color) {
        System.out.println("dog 有参构造...");
        this.name = name;
        this.color = color;
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
        System.out.println("dog -- setName()方法被调用...");
        this.name = name;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    /**
     * 获取
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置
     * @param color
     */
    public void setColor(String color) {
        System.out.println("dog -- setColor()方法被调用...");
        this.color = color;
    }
}
