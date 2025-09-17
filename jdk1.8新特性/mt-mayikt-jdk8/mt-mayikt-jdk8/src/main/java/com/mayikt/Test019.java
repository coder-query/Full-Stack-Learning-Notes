package com.mayikt;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @ClassName Test019
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test019 {
    public static void main(String[] args) {
        String userName = "mayikt";
        /*
        ofNullable 可以允许传递一个空值对象
        of 不允许传递一个空值对象
         */
//        Optional<String> optional = Optional.ofNullable(userName);
//        Optional<String> optional = Optional.of(userName);
//        System.out.println(optional.get());
//        boolean present = optional.isPresent();
        //false 返回false 则表示 值是为空 true 值不为空
//        System.out.println(present);
//        String uName = Optional.ofNullable(userName).orElse("meite");
//        System.out.println(uName);
        Boolean f = Optional.ofNullable(userName).filter(s -> "mayikt".equals(s)).isPresent();
        System.out.println(f);
    }
}
