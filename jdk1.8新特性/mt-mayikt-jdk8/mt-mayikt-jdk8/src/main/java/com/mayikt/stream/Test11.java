package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @ClassName Test11
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test11 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(10, 50, 30, 10);
        Optional<Integer> reduce = integerStream.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a1, Integer a2) {
                return a1 + a2;
            }
        });
//        System.out.println(reduce.get());

        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));
        userEntities.add(new UserEntity("liusi", 21));
        userEntities.add(new UserEntity("wangmazi", 19));
        userEntities.add(new UserEntity("xiaoke", 19));
        userEntities.add(new UserEntity("xiaoan", 21));
        Stream<UserEntity> stream = userEntities.stream();
        Optional<UserEntity> sum = stream.reduce((user1, user2) -> {
            UserEntity userEntity = new UserEntity("sum", user1.getAge() + user2.getAge());
            return userEntity;
        });
        System.out.println(sum.get().getAge());
    }
}
