package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Test09
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test09 {
    public static void main(String[] args) {
//        userEntities.add(new UserEntity("xiaowei", 16));
//        userEntities.add(new UserEntity("xiaowei", 16));
        // new对象
        UserEntity user1 = new UserEntity("xiaowei", 16);
        UserEntity user2 = new UserEntity("xiaowei", 16);
        String str1 = "xiaowei";
        String str2 = "xiaowei";
        /**
         * 在默认的情况下 equals 方法比较两个对象的内存地址是否相等
         */
        System.out.println(user1.equals(user2));
//        System.out.println(str1.equals(str2));
        /**
         * set集合底层依赖于 map集合实现防重复key  map集合底层基于equals比较防重复的结合hashCode
         */
//        HashSet<String> strings = new HashSet<>();
//        strings.add()
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(user1, "a");
        objectObjectHashMap.put(user2, "b");
        objectObjectHashMap.forEach((k, v) -> {
            System.out.println(k + "," + v);
        });
    }
}
