package com.it.E_集合的线程安全问题.ArrayList;

import java.util.UUID;
import java.util.Vector;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 13:51
 */
public class Vector_Test {
    public static void main(String[] args) {
        Vector<String> vector = new Vector();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                vector.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(Thread.currentThread().getName()+"-->"+vector);
            },String.valueOf(i)).start();
        }
    }
}
