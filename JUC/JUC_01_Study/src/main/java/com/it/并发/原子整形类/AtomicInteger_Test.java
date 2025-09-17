package com.it.并发.原子整形类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/20 星期四 16:13
 */
public class AtomicInteger_Test {
    private static AtomicInteger num = new AtomicInteger(0);
    public static void incr() {
        num.getAndAdd(1);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10_0000_0000; i++) {
            new Thread(()->{
                incr();
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(num);
    }
}
