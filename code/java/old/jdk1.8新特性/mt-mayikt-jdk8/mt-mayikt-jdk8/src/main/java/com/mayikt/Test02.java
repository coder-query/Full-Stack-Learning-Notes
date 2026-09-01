package com.mayikt;

import com.mayikt.service.OrderService;

/**
 * @ClassName Test02
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test02 {
    public static void main(String[] args) {
        //1.使用匿名内部类的形式调用OrderService 中get方法
//        OrderService orderService = new OrderService() {
//            @Override
//            public void get() {
//                System.out.println("get");
//            }
//        };
//        orderService.get();
        ((OrderService) () -> System.out.println("get")).get();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ",run")).start();

//        new JDK8InterfaceImpl().toString()
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })
    }
}
