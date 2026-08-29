package com.并发JUC.A_线程基础.a_线程的启动和终止.线程的创建方式;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 14:05
 */
public class RunnableTest {
    public static void main(String[] args) throws InterruptedException {
        Test_02 test02 = new Test_02();

        new Thread(test02).start();

        Thread.sleep(1000);
        System.out.println("我是main线程...");
    }
}

class Test_02 implements Runnable {
    @Override
    public void run() {
        System.out.println("我是实现Runnable接口的线程类,新起的线程...");
    }
}
