package com.mayikt;

/**
 * @ClassName Test08
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test08 {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("我是子线程")).start();

    }
}
