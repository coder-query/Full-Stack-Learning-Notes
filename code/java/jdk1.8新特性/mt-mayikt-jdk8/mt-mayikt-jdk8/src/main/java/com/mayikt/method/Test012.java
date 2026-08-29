package com.mayikt.method;

import com.mayikt.service.MessageInterface;

/**
 * @ClassName Test012
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test012 {
    public static void main(String[] args) {
        // 1.最原生匿名内部类调用方式
//        MessageInterface messageInterface = new MessageInterface() {
//            @Override
//            public void get(Integer a) {
//                System.out.println("geta:" + a);
//            }
//        };
//        messageInterface.get(1);

        // 在Lambda表达式中方法体直接引入方法
//        MessageInterface messageInterface2 = (a) -> Test012.staticGet(a);
//        // 方法引入 --实际上就是我们 Lambda表达式中方法体直接引入方法---
//        messageInterface2.get(2);
//        // 方法引入写法
//        MessageInterface messageInterface3 = Test012::staticGet;
//        messageInterface3.get(10,20);
        /**
         *Test012::staticGet为何能够等于
         * (a) -> Test012.staticGet(a);
         */
    }

    public static String  staticGet(Integer a, Integer b) {
        System.out.println("staticGet,a:" + a);
        return "123456";
    }
}
