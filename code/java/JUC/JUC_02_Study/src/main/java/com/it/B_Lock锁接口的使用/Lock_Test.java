package com.it.B_Lock锁接口的使用;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 10:44
 */
public class Lock_Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 100; i++)
                ticket.sale();
        },"A窗口").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++)
                ticket.sale();
        },"B窗口").start();

    }
}
class Ticket{
    private int number = 100;
    private final ReentrantLock lock =  new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+number--+"票，剩余"+number+"票");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}