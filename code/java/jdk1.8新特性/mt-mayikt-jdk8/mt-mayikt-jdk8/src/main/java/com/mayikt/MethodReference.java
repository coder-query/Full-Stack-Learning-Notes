//package com.mayikt;
//
//import com.mayikt.service.MessageInterface;
//
//import java.util.function.Consumer;
//
///**
// * @ClassName MethodReference
// * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
// * @Version V1.0
// **/
//public class MethodReference {
//    public static void main(String[] args) {
//        // 1.使用匿名内部类的形式调用
//        MethodReference methodReference = new MethodReference();
////        new MessageInterface() {
////            @Override
////            public void get() {
////                methodReference.getMethod();
////            }
////        };
//        //2.Lambda写法
////        MessageInterface messageInterface2 = () -> {
////            methodReference.getMethod();
////        };
////        messageInterface2.get();
//        // 3.改成方法引入写法 对象方法引入
////        MessageInterface messageInterface = methodReference::getMethod;
////
////        MessageInterface messageInterface2 = () -> {
////            MethodReference.getStaticMethod();;
////        };
//
//        MessageInterface messageInterface4 = MethodReference::getStaticMethod;
//        messageInterface4.get();
//
//    }
//
//    public void getMethod() {
//        System.out.println("我是方法引入 我是普通方法");
//    }
//
//    public static void getStaticMethod() {
//        System.out.println("我是方法引入 我是static普通方法");
//    }
//
//}
