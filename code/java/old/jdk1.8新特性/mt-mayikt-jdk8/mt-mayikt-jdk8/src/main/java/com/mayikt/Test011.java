package com.mayikt;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @ClassName Test011
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test011 {
    public static void main(String[] args) {
        String name = "mayikt";
//        if (name != null) {
//            System.out.println(name);
//        }
        //ifPresent 消费 name值 打印值 ifPresent值不为空的情况下 执行消费者函数接口
        // 值为空的情况下 不会执行消费者函数接口
        //Optional+方法引入+函数接口和Lambda
        Optional.ofNullable(name).ifPresent(System.out::println);
    }
}
