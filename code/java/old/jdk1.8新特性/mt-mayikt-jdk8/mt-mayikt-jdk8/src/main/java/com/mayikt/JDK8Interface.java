package com.mayikt;

/**
 * @ClassName JDK8Interface
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public interface JDK8Interface {
    /**
     * 抽象方法 需要子类实现
     * 默认类型：public、abstract
     */
    void add();

    /**
     * default方法
     */
    default void defaultGet() {
        System.out.println("我是default方法");
    }

    static void staticGet() {
        System.out.println("我是default方法");
    }
}
