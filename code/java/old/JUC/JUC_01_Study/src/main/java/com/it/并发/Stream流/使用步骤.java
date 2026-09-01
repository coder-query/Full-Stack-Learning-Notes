package com.it.并发.Stream流;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 23:12
 */
public class 使用步骤 {
    public static void main(String[] args) {
        /// 单列集合
        Test.single_row();
        /// 双列集合
        Test.double_row();
        /// 数组
        Test.array_stream();
        /// 零散数据
        Test.extra_stream();
    }
}

class Test {

    /**
     * 单列集合
     */
    public static void single_row() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        System.out.println("----------------单列集合--------------------------");
        arrayList.stream().forEach((s) -> {
            System.out.println(s);
        });

    }
    /**
     * 双列集合
     */
    public static void double_row() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        stringObjectHashMap.put("1","111");
        stringObjectHashMap.put("2","222");
        stringObjectHashMap.put("3","333");

        /// 方式一 : key键转换成Stream流
        System.out.println("---------------方式一 : key键转换成Stream流----------------------------");
        stringObjectHashMap.keySet().stream().forEach((s) -> {
            System.out.println(s);
        });

        /// 方式二 : value值转换成Stream流
        System.out.println("-----------------方式二 : value值转换成Stream流--------------------------");
        stringObjectHashMap.values().stream().forEach((s) -> {
            System.out.println(s);
        });

        /// 方式三 : key和value都转换成Stream流
        System.out.println("----------------方式三 : key和value都转换成Stream流---------------------------");
        stringObjectHashMap.entrySet().stream().forEach((s) -> {
            System.out.println(s.getKey()+"--->"+s.getValue());
        });

    }
    public static void array_stream(){
        int [] array = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("----------------数组转换成Stream流---------------------------");
        Arrays.stream(array).forEach((s) -> {
            System.out.println(s);
        });
    }
    public static void extra_stream(){
        System.out.println("----------------零散数据转换成Stream流---------------------------");
        Stream.of(1,2,3,4,5,6,7,8,9,10).forEach((s) -> {
            System.out.println(s);
        });
    }
}
