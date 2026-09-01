package com.it.并发.成员方法;

public class 插队线程 {
    public static void main(String[] args) throws InterruptedException {

        yThread1 t1 = new yThread1();
        t1.setName("插队线程");
        t1.start();

        /**
         * 插队线程
         * 在当前线程执行之前先执行, 插队线程执行完之后再执行当前线程
         * 当前线程 : main 线程
         */
        t1.join();

        for (int i = 0; i < 10; i++) {
            System.out.println("主线程:"+"--->"+i);
        }

    }
}
//执行10次循环
class yThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
