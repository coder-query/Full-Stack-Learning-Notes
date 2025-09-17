package com.并发JUC.A_线程基础.e_线程安全同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Thread {
    /**
     * 使用Lock框架实现同步机制的标准格式
     * Lock objectLock = new 子类();  /// 锁对象
     * objectLock.lock() /// 在监视区域开始的时候,加锁
     * try{
     * /// 监视区域,临界区
     * }finally{
     * objectLock.unlock(); ///在监视区域结束时,释放锁
     * }
     */
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
    private final Lock lock = new ReentrantLock();

    /// 获取锁
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
