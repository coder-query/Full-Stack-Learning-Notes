package com.it.A_Synchronized的复习使用;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 10:29
 */
public class Synchronized_Test {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 100; i++)
                resource.sale();
        }, "A窗口").start();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 100; i++)
                resource.sale();
        }, "B窗口").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++)
                resource.sale();
        }, "C窗口").start();
    }
}

class Resource {
    private int money = 100;

    /// 100张票
    public void sale() {
        synchronized (this) {
            if (money > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + (--money));
            }
        }
    }
}
