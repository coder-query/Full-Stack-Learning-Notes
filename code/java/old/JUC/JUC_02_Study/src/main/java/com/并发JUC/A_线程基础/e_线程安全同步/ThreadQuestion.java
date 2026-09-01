package com.并发JUC.A_线程基础.e_线程安全同步;

public class ThreadQuestion {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();// 一份资源

        Class<Ticket> ticketClass = Ticket.class;

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程A-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程B-->").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.sale();
            }
        }, "线程C-->").start();
    }
}

class Ticket {
    private int num = 100;  // 100 张票

    public void sale() {
        synchronized (this) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + (num--) + "张票");
            }
        }
    }

}
