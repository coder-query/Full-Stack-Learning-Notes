package com.it.并发.创建线程的方式;

public class 实现Runnable接口 {
    /**
     * 实现Runnable接口
     * 1.创建一个类实现Runnable接口
     * 2.重写run()方法
     */
    public static void main(String[] args) {

        // lamada
        // 类 接口（函数接口 Function Interface）
        //

        // new Thread(Runnable对象)  --- new  ---> 类 ---》对象

        Thread thread = new Thread();
        // thread ： 对象的名字（方法体）

        // 接口  ---> 对象
        // 匿名对象()
        new Thread(() -> {
            // 方法体
        });


        Thread thread1 = new Thread(new MyRunnable());
        thread1.setName("线程1");
        thread1.start();
        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable()).start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
