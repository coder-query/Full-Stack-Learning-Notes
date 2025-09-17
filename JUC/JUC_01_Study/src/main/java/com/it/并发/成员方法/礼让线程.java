package com.it.并发.成员方法;

public class 礼让线程 {
    /**
     * 礼让线程
     *  礼让线程，释放当前cpu执行权,和其他线程形成公平竞争cpu资源
     *  礼让线程，不一定成功，看cpu心情
     */
    public static void main(String[] args) {
        MThread1 mThread1 = new MThread1();
        mThread1.setName("礼让线程");
        mThread1.start();

        MThread2 mThread2 = new MThread2();
        mThread2.setName("霸道主义");
        mThread2.start();
    }
}
//执行100次循环
class MThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
            Thread.yield(); //礼让线程
        }
    }
}
//执行100次循环
class MThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}