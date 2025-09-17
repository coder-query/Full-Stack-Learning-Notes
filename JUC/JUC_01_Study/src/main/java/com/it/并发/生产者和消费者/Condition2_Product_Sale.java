package com.it.并发.生产者和消费者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condition2_Product_Sale {
    public static void main(String[] args) {
        Resource3 resource3 = new Resource3();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                resource3.printfA();
            }
        }, "线程A:-->>").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                resource3.printfB();
            }
        }, "线程B:-->>").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                resource3.printfC();
            }
        }, "线程C:-->>").start();
    }
}

class Resource3 {
    /// 创建一个锁
    ReentrantLock reentrantLock = new ReentrantLock();
    /// 创建多个监视器
    Condition condition1 = reentrantLock.newCondition();
    Condition condition2 = reentrantLock.newCondition();
    Condition condition3 = reentrantLock.newCondition();
    /// 当flag为1时,A线程被唤醒执行
    /// 当flag为2时,B线程被唤醒执行
    /// 当flag为3时,C线程被唤醒执行
    int flag = 1;

    public void printfA() {
        reentrantLock.lock();
        try {
            while (flag != 1) {
                condition1.await(); /// A等待
            }
            System.out.println(Thread.currentThread().getName() + "-->AAA");
            flag = 2;
            condition2.signal(); /// 唤醒B线程
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printfB() {
        reentrantLock.lock();
        try {
            while (flag != 2) {
                condition2.await(); /// A等待
            }
            System.out.println(Thread.currentThread().getName() + "-->BBB");
            flag = 3;
            condition3.signal(); /// 唤醒B线程
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printfC() {
        reentrantLock.lock();
        try {
            while (flag != 3) {
                condition3.await(); /// A等待
            }
            System.out.println(Thread.currentThread().getName() + "-->CCC");
            flag = 1;
            condition1.signal(); /// 唤醒B线程
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }
}