package com.mayikt;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @ClassName Test020
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test020 {
    public static void main(String[] args) {
        String userName = "mayikt";
//        if(userName!=null){
//
//        }
        Optional<String> optional = Optional.ofNullable(userName);
//        boolean present = optional.isPresent();
//        if (present) {
//            System.out.println(optional.get());
//        }
        //userName不为空的情况下 直接调用accept 打印值 serName==null的情况下 就不会执行accept
//        optional.ifPresent(s -> System.out.println(s));
        optional.ifPresent(System.out::print);
    }
}
