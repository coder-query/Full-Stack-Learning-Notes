//package com.mayikt.method;
//
//import com.mayikt.service.MessageInterface;
//
///**
// * @ClassName Test009
// * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
// * @Version V1.0
// **/
//public class Test009 {
//    public static void main(String[] args) {
//        //1.匿名内部类的写法
//        Test009 test009 = new Test009();
////        MessageInterface messageInterface = new MessageInterface() {
////            @Override
////            public void get() {
////                test009.get();
////            }
////        };
////        messageInterface.get();
////        ;
////        MessageInterface messageInterface = () -> {
////            test009.get();
////            ;
////        };
////        messageInterface.get();
//        MessageInterface messageInterface = test009::get;
//        messageInterface.get(1);
//    }
//
//    public void get(Integer a) {
//        System.out.println("方法引入get方法:" + a);
//    }
//}
