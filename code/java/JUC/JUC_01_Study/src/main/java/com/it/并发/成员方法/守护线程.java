package com.it.并发.成员方法;

public class 守护线程 {
    /**
     * 守护线程
     * 当非守护线程执行结束,所有的守护线程也会跟着执行结束
     * 案例场景 :
     *          在发送文件给好友时,文件还在发送中,我此时关闭窗口,则发送文件也被中断结束
     *          上面的场景中, 守护线程就是用来处理文件发送的线程, 窗口聊天是非守护线程
     * @param args
     */
    public static void main(String[] args) {

        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("我是非守护线程...");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("我是守护线程...");
        myThread2.setDaemon(true);
        myThread2.start();

    }
}
//执行10次循环
class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
//执行100次循环
class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
