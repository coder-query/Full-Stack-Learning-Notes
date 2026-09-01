package com.mayikt;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @ClassName Test010
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test010 {
    public static void main(String[] args) {
        Integer a = 19;
        //ofNullable允许我们对象是为空值
//        Optional<Integer> a1 = Optional.ofNullable(a);
        //of不允许有空值
//        Optional<Integer> a2 = Optional.of(a);
//        System.out.println(a1.get());
        //isPresent判断我们值是否为空 为空的情况下 返回 false 不为空的情况下 true
//        System.out.println(a1.isPresent());
//        System.out.println(a1.get());
//        Integer a2 = Optional.ofNullable(a).orElse(20);
//        System.out.println(a2);

        boolean r = Optional.ofNullable(a).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer a) {
                return a > 18;
            }
        }).isPresent();
        System.out.println(r);
    }
}
