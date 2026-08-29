package com.it;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 帅宏-coding @Money java_offer_13k
 * @date 2025/4/6 星期日 11:09
 */
public class Main {
  public static void main(String[] args) {
    CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    copyOnWriteArrayList.add(null);
    ArrayList<Object> list = new ArrayList<>();
    list.add(new Object());
    /** HashMap */
    HashMap<Object, Object> hashMap = new HashMap<>(17); // 没有在堆上建立新对象
    hashMap.put(null, "zsh666");
    System.out.println(hashMap.put("zsh", "ynn"));
    System.out.println();

    hashMap.get("zsh");
    hashMap.size();
    System.out.println(hashMap.containsKey("zsh"));

    /** ConcurrentHashMap */
    ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
    concurrentHashMap.put("zsh", null);
    concurrentHashMap.get("");
    concurrentHashMap.size();
    System.out.println(concurrentHashMap.containsKey("zsh"));



    String [] strArr = new String[10];

    // 类 -》 对象
    NodeTest<String, String> stringStringNodeTest = new NodeTest<>("zsh", "zsh666");
    NodeTest<String, String> [] nodeTest = new NodeTest[10];
    nodeTest[0] = stringStringNodeTest;




  }
}
