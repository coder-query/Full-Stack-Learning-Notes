package com.mayikt.service;

/**
 * @ClassName MyFunctionalInterface
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@FunctionalInterface
public interface MyFunctionalInterface {
    void get();

    default void defaultAdd() {
        System.out.println("我是默认的方法");
    }

    /**
     * object父类中的方法可以在 函数接口中重写
     *
     * @return
     */
    String toString();
}
