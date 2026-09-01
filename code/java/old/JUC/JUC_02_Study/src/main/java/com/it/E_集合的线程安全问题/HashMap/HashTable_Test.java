package com.it.E_集合的线程安全问题.HashMap;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 20:19
 */
public class HashTable_Test {
    public static void main(String[] args) {
        Hashtable<String,String> hashtable_demo = new Hashtable<>();
        for (int i = 0; i < 1000; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                for(int j = 0; j < 10; j++){
                    hashtable_demo.put(key,"value" + UUID.randomUUID().toString().substring(0,5));
                }
                System.out.println(Thread.currentThread().getName() + "=>" +hashtable_demo);
            },"Thread"+i).start();
        }
    }
}
