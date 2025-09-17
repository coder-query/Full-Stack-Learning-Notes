package com.it;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TestMain {
    public static void main(String[] args) {

        //使用ArrayList存储
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("张三");
        arrayList.add("李四");
        arrayList.add("王五");
        //输出
        for (String s : arrayList) {
            System.out.println(s);
        }


        //使用LinkedList存储
        LinkedList<String> list = new LinkedList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        //输出
        for (String s : list) {
            System.out.println(s);
        }

        //使用HashMap存储
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("name", "李四");
        map.put("name", "王五");
        //输出
        for (String s : map.keySet()) {
            System.out.println(s);
        }


    }
}
