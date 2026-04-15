package com.it.并发.原子整形类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/20 星期四 16:13
 * <p>
 * synchronized
 * 类对象 .class
 * 对象锁 new Object()
 * ReentrantLock
 *
 *
 */
public class AtomicInteger_Test {
    private int num = 0;

    public synchronized void incr() {
        num++;
    }

    public int getNum() {
        return num;
    }

    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock();
//        for (int i = 0; i < 10_0000; i++) {
//            new Thread(() -> {
//                try {
//                    lock.lock();
//                    incr();
//                } finally {
//                    lock.unlock();
//                }
//            }).start();
//        }
        long startTime = System.currentTimeMillis();

        AtomicInteger_Test atomicIntegerTest = new AtomicInteger_Test();
        for (int i = 0; i < 10_0000; i++) {
            new Thread(() -> {
                atomicIntegerTest.incr();
            }).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("耗时：" + (endTime - startTime) + "ms");
        System.out.println(atomicIntegerTest.getNum());
    }


}


//    private static AtomicInteger num = new AtomicInteger(0);


//        num.getAndIncrement(); // num++;


// synchronized的括号内放入的是公共可访问到的对象，且唯一的
// 类对象锁
//        synchronized (AtomicInteger_Test.class) {
//            num++;
//        }


//         10万
//         >
//         < ✅️
//         =
//         synchronized
//         类对象锁
//         对象锁// gc root
//        ReentrantLock lock = new ReentrantLock();
//        for (int i = 0; i < 10_0000; i++) {
//            new Thread(() -> {
//                try {
//                    lock.lock();
//                    incr();
//                } finally {
//                    lock.unlock();
//                }
//            }).start();
//        }
//        Object o = new Object();

// set nx
// set k1 v1 (第一次执行)
// set k1 v2 (第二次执行)
// v1？ v2？

// setnx k1 v1
// setnx k1 v2
