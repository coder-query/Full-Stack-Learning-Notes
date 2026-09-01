package com.it.并发.zsh_juc_new.原理;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final ReentrantLock reentrantLock1 = new ReentrantLock();

    public static void main(String[] args) {

        // 锁分类
        // 公平锁  非公平锁
        // 可重入锁  非可重入锁

        // Async
        // sync

        // 默认非公平锁

//        ReentrantLock reentrantLock2 = new ReentrantLock(true);

        reentrantLock1.lock();

        // 业务逻辑

        reentrantLock1.lock();

        reentrantLock1.unlock();

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
