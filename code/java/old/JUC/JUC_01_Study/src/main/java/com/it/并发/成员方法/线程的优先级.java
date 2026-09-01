package com.it.并发.成员方法;


public class 线程的优先级 {
    /**
     * 线程的优先级
     * 数字越大,抢到CPU的概率就越高,线程越先执行
     * 但是也会出现执行交替的情况,这是个概率问题
     * @param args
     */
    public static void main(String[] args) {
        Thread1 myThread1 = new Thread1();
        myThread1.setPriority(Thread.MAX_PRIORITY);
        myThread1.setName("线程1");
        myThread1.start();

        Thread2 myThread2 = new Thread2();
        myThread2.setPriority(Thread.MIN_PRIORITY);
        myThread2.setName("线程2");
        myThread2.start();
    }
}

//执行10次循环
class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
//执行100次循环
class Thread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
