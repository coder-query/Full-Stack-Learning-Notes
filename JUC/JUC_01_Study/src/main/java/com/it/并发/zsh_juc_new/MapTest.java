package com.it.并发.zsh_juc_new;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MapTest {
    public static void main(String[] args) throws InterruptedException {

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("key", "value");
//
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.addAndGet()
//        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
//        objectObjectConcurrentHashMap.put("key", "value");
//        Object o = objectObjectConcurrentHashMap.putIfAbsent("key", "value2");   // setnx
//        System.out.println(o);
//        System.out.println(objectObjectConcurrentHashMap);
//        objectObjectConcurrentHashMap.putIfAbsent("key", "value2");

        // 懒加载  ： 用到的时候才回去创建
        // new ThreadPoolExecutor();
        // exce.execute();
        // 饿汉式  ： 创建的时候就创建好

//        ArrayList<String> stringArrayList = new ArrayList<>();  // 懒加载
        ArrayList<String> stringArrayList = new ArrayList<>(2);  // 饿汉式
        stringArrayList.add("1");   // 默认容量 10 // 扩容 1.5

        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("1");


        // 写时复制
        // 读多写少
        CopyOnWriteArrayList<String> stringCopyOnWriteArrayList = new CopyOnWriteArrayList<>();
        stringCopyOnWriteArrayList.add("1");  // ReentrantLock  ---》 cas

        new Thread(() -> {
            stringCopyOnWriteArrayList.add("2");
        }).start();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return "1";
        });
        Thread.sleep(300000000);
    }
}
