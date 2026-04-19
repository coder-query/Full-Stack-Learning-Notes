package com.it.并发.zsh_juc_new;

/**
 *
 * @author shuaihong-coding
 * @date 2026-03-25 10:08
 */
public class Test01 {

    public static boolean flag1 = true;
    public static volatile boolean flag2 = true;

//    public volatile static boolean flag1 = true;

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        new Thread(() -> {
            while (flag1) {
                flag2 = false;
            }
        }).start();

//        synchronized (o) {
//            try {
//                Thread.sleep(100L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        Thread.sleep(1000);
        flag1 = false;
//        System.out.println("flag1 修改为 false");
    }
}
