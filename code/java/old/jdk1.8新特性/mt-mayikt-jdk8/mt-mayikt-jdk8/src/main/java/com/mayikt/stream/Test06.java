package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @ClassName Test06
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test06 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        userEntities.add(new UserEntity("liusi", 21));
        userEntities.add(new UserEntity("wangmazi", 19));
        Stream<UserEntity> stream = userEntities.stream();
        stream.sorted((o1, o2) -> o2.getAge() - o1.getAge()).forEach((userEntity -> System.out.println(userEntity.toString())));
    }
}
