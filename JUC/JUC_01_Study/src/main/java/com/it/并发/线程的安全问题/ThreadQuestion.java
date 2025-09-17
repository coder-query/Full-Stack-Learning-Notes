package com.it.并发.线程的安全问题;

public class ThreadQuestion {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 100; i++){
                ticket.sale();
            }
        },"线程A-->").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++){
                ticket.sale();
            }
        },"线程B-->").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++){
                ticket.sale();
            }
        },"线程C-->").start();
    }
}

class Ticket{
    private int num = 100;
    public void sale(){
        if(num > 0){
            System.out.println(Thread.currentThread().getName()+"正在出售第"+(num--)+"张票");
        }
    }
}
