package com.it.并发.线程的生命周期;

public class ThreadLifeTime {
    /**
     * 线程的生命周期
     * 1. New --> new Thread(...)
     * 2. Runnable --> Thread.start()
     * 3. Waiting -->  调用阻塞方法,如sleep()、join()、wait()等
     * 4. Time_Waiting --> sleep(long time)、join()、wait(long time)
     * 5. Blocked --> 抢到锁的进入Runnable,没抢到进入锁阻塞Blocked状态
     * 6. Terminated --> 线程结束
     */
    public static void main(String[] args) {
        Thread.State state = Thread.currentThread().getState();
        System.out.println("线程状态：" + state);
        new Thread(()->{
            System.out.println("线程开始执行");
        }).start();
        System.out.println("线程开始创建");
    }
}
