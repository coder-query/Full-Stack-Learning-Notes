package com.mayikt.stream;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName Test02
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test02 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("mayikt", 20));
        userEntities.add(new UserEntity("meite", 28));
        userEntities.add(new UserEntity("zhangsan", 35));

        // list集合只有元素值 key list转换成map集合的情况下 指定key--user对象 username属性  value user对象
        /**
         * map<String(userName),UserEntity>
         */
        Stream<UserEntity> stream = userEntities.stream();
        /**
         * new Function<UserEntity(list集合中的类型), String(key map)>
         */
        Map<String, UserEntity> collect = stream.collect(Collectors.toMap(userEntity -> userEntity.getUserName(), userEntity -> userEntity));
        collect.forEach((BiConsumer) (o, o2) -> System.out.println(o + "," + o2));
    }
}
