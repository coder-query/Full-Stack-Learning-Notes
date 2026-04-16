package com.it.并发.zsh_juc_new.原理;

public class SynchronizedTest {
    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            System.out.println("线程开始");
        }
    }
}
