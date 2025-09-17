package com.并发JUC.A_线程基础.a_线程的启动和终止.线程的创建方式;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 14:03
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Test_01 test01 = new Test_01();
//        test01.setName("子线程_01");
//        System.out.printf("线程id = %d\n", test01.getId());
//        System.out.printf("线程name = %s\n", test01.getName());
        test01.start(); // 启动线程 调用 run()

        Thread.sleep(1000);  // main主线程调用的sleep
        System.out.println("我是main线程...");
    }
}

// 线程类 ---> 线程对象 ---> 类 --> new 对象
class Test_01 extends Thread {
    @Override
    public void run() {
        System.out.println("我是继承Thread类,新起的线程...");
    }
}
