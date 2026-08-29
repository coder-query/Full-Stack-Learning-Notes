package com.mayikt;

/**
 * @ClassName Test09
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test09 {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("获取到线程名称:" + Thread.currentThread().getName() + "，子线程");
//            }
//        }).start();

        new Thread(() -> System.out.println("获取到线程名称:" + Thread.currentThread().getName() + "，子线程")).start();
    }
}
