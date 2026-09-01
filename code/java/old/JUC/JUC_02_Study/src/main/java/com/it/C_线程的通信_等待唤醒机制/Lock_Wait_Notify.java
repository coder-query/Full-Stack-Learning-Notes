package com.it.C_线程的通信_等待唤醒机制;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 11:14
 */
public class Lock_Wait_Notify {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BBB").start();
    }
}

class Share{
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition reentrantLock_condition = reentrantLock.newCondition();
    private int stock = 0;
    public  void incr() throws InterruptedException {
        reentrantLock.lock();
        try {
            while(stock != 0){
                reentrantLock_condition.await();
            }
            stock++;
            System.out.println(Thread.currentThread().getName()+"已经进货.....现在库存数量=>"+stock);
            reentrantLock_condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }
    public void decr() throws InterruptedException {
        reentrantLock.lock();
        try {
            while(stock == 0){
                reentrantLock_condition.await();
            }
            stock--;
            System.out.println(Thread.currentThread().getName()+"正在卖出商品....现在库存数量为"+stock+"请赶快进货....");
            reentrantLock_condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }
}