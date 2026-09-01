package com.mayikt.method;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @ClassName Test05
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test05 {
    public static void main(String[] args) {
        // 优化前
        String mayiktName = "meite";
        if (mayiktName != null) {
            System.out.println(mayiktName);
        }
        //优化后
        Optional<String> mayiktName2 = Optional.ofNullable(mayiktName);
//        // 当value 不为空时，则不会调用
//        mayiktName2.ifPresent(s -> System.out.println(s));
        mayiktName2.ifPresent(System.out::print);
    }
}
