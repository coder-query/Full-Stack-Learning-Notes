package com.mayikt.method;

import com.mayikt.service.MessageInterface;

/**
 * @ClassName Test013
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test013 {
    public static void main(String[] args) {
        Test013 test013 = new Test013();
//        MessageInterface messageInterface = new MessageInterface() {
//
//            @Override
//            public String get(Integer a, Integer b) {
//                return test013.objGet(a, b);
//            }
//        };
//
//        String s = messageInterface.get(1, 2);
//        System.out.println(s);

//        MessageInterface messageInterface2 = (a, b) -> test013.objGet(a, b);
        MessageInterface MessageInterface3 = test013::objGet;
        String s = MessageInterface3.get(1, 2);
        System.out.println(s);
    }

    public String objGet(Integer a, Integer b) {
        System.out.println("objGet,a:" + a + ",b:" + b);
        return a + b + "";
    }
}
