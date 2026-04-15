package com.it.并发.线程的安全问题;

public class Synchronized_Thread {
    /**
     * 线程安全问题
     * 锁对象切记一定要唯一 : static关键字就是唯一的
     * synchronized
     * 1.  同步代码块
     * 2. 同步方法 : 锁的对象是this,也就是调用本方法的对象
     *
     * @param args
     */
    public static void main(String[] args) {

        Ticket1 ticket = new Ticket1();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale1();
            }
        }, "线程A-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale1();
            }
        }, "线程B-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale1();
            }
        }, "线程C-->").start();
    }
}

class Ticket1 {
    int ticket = 100;

    /**
     * 同步方法
     * 这里我的Ticket对象只在main()方法中new了一次
     * 锁的对象是ticket,所以是同一个对象,所以是线程安全的
     */
    public synchronized void sale1() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "正在出售第" + (ticket--) + "张票");
        }
    }


    public void sale2() {
        /**
         * 同步代码块
         * 锁对象是Ticket字节码对象,唯一
         */
        synchronized (Ticket1.class) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + (ticket--) + "张票");
            }
        }
    }
}
