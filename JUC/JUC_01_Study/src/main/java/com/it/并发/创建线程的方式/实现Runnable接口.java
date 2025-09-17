package com.it.并发.创建线程的方式;

public class 实现Runnable接口 {
    /**
     * 实现Runnable接口
     *    1.创建一个类实现Runnable接口
     *    2.重写run()方法
     */
    public static void main(String[] args){
        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable()).start();
    }
}
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
