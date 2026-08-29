package com.it;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/6 星期日 11:10
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Object());

        /**
         * HashMap
         */
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("", "");
        hashMap.get("");
        hashMap.size();
        hashMap.containsKey("");

        /**
         * CurrentHashMap
         */
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("", "");
        concurrentHashMap.get("");
        concurrentHashMap.size();
        concurrentHashMap.containsKey("");

        System.out.println("zzzzzzzzzzzzz");
    }
}