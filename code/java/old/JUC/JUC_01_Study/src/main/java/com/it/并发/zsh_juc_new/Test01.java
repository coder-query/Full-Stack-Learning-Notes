package com.it.并发.zsh_juc_new;

/**
 *
 * @author shuaihong-coding
 * @date 2026-03-25 10:08
 */
public class Test01 {

    public static boolean flag1 = true;
    public static volatile byte[] bytes = new byte[1024 * 128];

//    public volatile static boolean flag1 = true;

    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            while (flag1) {
                bytes[0] = 1;
                System.out.println(bytes[0]);
            }
            System.out.println("t1线程结束");
        }).start();

        Object o = new Object();
//        synchronized (o) {
//            try {
//                Thread.sleep(100L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }


        Thread.sleep(1000);
        flag1 = false;
        System.out.println("flag1 修改为 false");
    }
}
