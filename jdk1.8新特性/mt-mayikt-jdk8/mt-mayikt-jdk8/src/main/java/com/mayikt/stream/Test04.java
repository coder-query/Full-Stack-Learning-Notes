package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName Test04
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test04 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        Stream<UserEntity> stream = userEntities.stream();
//        boolean result = stream.allMatch(new Predicate<UserEntity>() {
//            @Override
//            public boolean test(UserEntity userEntity) {
//                return "mayikt".equals(userEntity.getUserName());
//            }
//        });
        boolean result = stream.anyMatch((user) -> !"mayikt".equals(user.getUserName()));
        System.out.println(result);
    }
}
