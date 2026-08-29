package com.it.D_线程间的定制有序通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 13:12
 */
public class Lock_Condition_Test {
    public static void main(String[] args) {
        Share share = new Share();

       new Thread(()->{
           for (int i = 0; i < 10; i++)
               share.printAA();
       }, "AA线程").start();

       new Thread(()->{
           for (int i = 0; i < 10; i++)
               share.printBB();
       }, "BB线程").start();

       new Thread(()->{
           for (int i = 0; i < 10; i++)
               share.printCC();
       }, "CC线程").start();
    }
}

class Share {
    private static int flag = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition reentrantLock_conditionAA = reentrantLock.newCondition();
    private final Condition reentrantLock_conditionBB = reentrantLock.newCondition();
    private final Condition reentrantLock_conditionCC = reentrantLock.newCondition();
    public void printAA(){
        reentrantLock.lock();
        try {
            while(flag != 0){
                reentrantLock_conditionAA.await();
            }
            System.out.println("我是AA,下一个打印BB....");
            flag = 1;
            reentrantLock_conditionBB.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printBB(){
        reentrantLock.lock();
        try {
            while(flag != 1){
                reentrantLock_conditionBB.await();
            }
            System.out.println("我是BB,下一个打印CC....");
            flag = 2;
            reentrantLock_conditionCC.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printCC(){
        reentrantLock.lock();
        try {
            while(flag != 2){
                reentrantLock_conditionCC.await();
            }
            System.out.println("我是CC,下一个打印AA....");
            flag = 0;
            reentrantLock_conditionAA.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }
}
