//package com.mayikt.method;
//
//import com.mayikt.service.MessageInterface;
//
///**
// * @ClassName Test01
// * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
// * @Version V1.0
// **/
//public class Test01 {
//    public static void main(String[] args) {
//        Test01 test01 = new Test01();
////        MessageInterface messageInterface = () -> {
////            test01.objGet();
////        };
////        messageInterface.get();
//        /**
//         * 方法引入规则：方法引入的方法返回类型 参数列表 必须要和函数接口保持一致
//         */
//        // 方法引入
////        MessageInterface messageInterface2 = Test01::staticGet;
////        messageInterface2.get();
//        MessageInterface messageInterface2 = test01::objGet;
//        messageInterface2.get();
//    }
//
//    public static void staticGet() {
//        System.out.println("staticGet");
//    }
//
//    public void objGet() {
//        System.out.println("objGet");
//    }
//}
