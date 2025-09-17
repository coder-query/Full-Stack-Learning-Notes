package com.it.并发.创建线程的方式;

public class 继承Thread类 {
    /**
     * 继承Thread类
     * 继承Thread类,重写run()
     */
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->>>"+i);
        }
    }
}