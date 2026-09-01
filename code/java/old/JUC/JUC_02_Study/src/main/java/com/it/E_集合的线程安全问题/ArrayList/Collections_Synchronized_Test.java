package com.it.E_集合的线程安全问题.ArrayList;

import java.util.*;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 13:44
 */
public class Collections_Synchronized_Test {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList();
        Collection<String> synchronizedCollection = Collections.synchronizedCollection(arrayList);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronizedCollection.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(Thread.currentThread().getName()+"-->" + synchronizedCollection);
            },String.valueOf(i)).start();
        }
    }
}
