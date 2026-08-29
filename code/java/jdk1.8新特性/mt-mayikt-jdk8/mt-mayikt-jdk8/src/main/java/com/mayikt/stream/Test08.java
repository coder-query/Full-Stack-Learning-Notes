package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName Test08
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test08 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        userEntities.add(new UserEntity("xiaowei", 16));
        userEntities.add(new UserEntity("mayikt_list", 109));
        userEntities.add(new UserEntity("mayikt_zhangsan", 110));
        userEntities.add(new UserEntity("lisi", 109));
        userEntities.add(new UserEntity("mayikt", 66));
        userEntities.add(new UserEntity("mayikt", 78));
        userEntities.add(new UserEntity("mayikt", 32));
        //要求：对数据流的数据实现降序排列、且名称为mayikt 获取后两位数据
        Stream<UserEntity> stream = userEntities.stream();
        stream.sorted((o1, o2) -> o2.getAge() - o1.getAge()).filter(userEntity -> "mayikt".equals(userEntity.getUserName())).skip(2)
                .forEach((userEntity -> System.out.println(userEntity)));
    }
}
