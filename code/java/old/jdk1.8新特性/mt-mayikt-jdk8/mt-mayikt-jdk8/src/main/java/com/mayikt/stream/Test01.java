package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName Test01
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        /**
         * user 对象属性值都是相等的 但是new 两个对象 这两个对象的 内存地址 是不一样的
         */
//        UserEntity user = new UserEntity("xiaowei", 16);
//        userEntities.add(user);
//        userEntities.add(user);
        userEntities.add(new UserEntity("xiaowei", 16));
        userEntities.add(new UserEntity("xiaowei", 16));
        userEntities.forEach((t) -> {
            System.out.println(t.toString());
        });
        System.out.println("取出重复...");
//        /**
//         * 创建stream方式两种
//         * 1.串行流stream() 单线程
//         * 2.并行流parallelStream() 多线程
//         * 并行流parallelStream 比串行流stream()效率要高的
//         */
        Stream<UserEntity> stream = userEntities.stream();
        // 转换成set集合
        Set<UserEntity> setUserList = stream.collect(Collectors.toSet());
        setUserList.forEach(userEntity -> {
            System.out.println(userEntity.toString());
        });

        




    }
}
