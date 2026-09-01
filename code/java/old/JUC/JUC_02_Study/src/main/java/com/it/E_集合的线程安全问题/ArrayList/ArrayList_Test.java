package com.it.E_集合的线程安全问题.ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 13:30
 */
public class ArrayList_Test {
    public static void main(String[] args) {

        /**
         * ConcurrentModificationException : 并发修改异常
         */

       List<String> arrayList = new ArrayList();
       for (int i = 0; i < 10; i++) {
           new Thread(()->{
               arrayList.add(Thread.currentThread().getName() +UUID.randomUUID().toString().substring(0,5));
               System.out.println(arrayList);
           },String.valueOf(i)).start();
       }

    }
}
