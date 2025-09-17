package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @ClassName Test18
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test18 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        userEntities.add(new UserEntity("liusi", 21));
        userEntities.add(new UserEntity("wangmazi", 19));
        userEntities.add(new UserEntity("xiaoke", 19));
        userEntities.add(new UserEntity("xiaoan", 21));
        // 并行与串行有什么区别 并行流底层如何实现的呢？
//        Stream<UserEntity> stream = userEntities.stream();
//        stream.limit(2).forEach((t) -> {
//            System.out.println(t.toString());
//        });
        Stream<UserEntity> userEntityStream = userEntities.parallelStream();
        userEntityStream.limit(2).forEach((t) -> {
            System.out.println(t);
        });
    }
}
