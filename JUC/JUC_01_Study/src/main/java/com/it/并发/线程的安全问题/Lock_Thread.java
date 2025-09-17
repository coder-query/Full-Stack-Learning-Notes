package com.it.并发.线程的安全问题;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Thread {
    public static void main(String[] args) {
        Ticket3 ticket = new Ticket3();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程A-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程B-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程C-->").start();
    }
}

class Ticket3 {
    private int num = 100;
    Lock lock = new ReentrantLock(); /// 获取锁


    public void sale() {
        try {
            lock.lock();  /// 上锁
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + (num--) + "张票");
            }
        } finally {
            lock.unlock();  /// 解锁
        }
    }
}
