package com.it.E_集合的线程安全问题.ArrayList;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 14:01
 */
public class CopyOnWriteArrayList_Test {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> stringCopyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                stringCopyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(Thread.currentThread().getName() + "---> "+stringCopyOnWriteArrayList);
            },String.valueOf(i)).start();
        }
    }
}
