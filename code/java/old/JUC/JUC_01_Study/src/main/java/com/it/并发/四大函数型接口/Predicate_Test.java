package com.it.并发.四大函数型接口;

import java.util.function.Predicate;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 11:45
 */
public class Predicate_Test {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                /// 判断传入的对象是不是为空
                return !o.isEmpty();
            }
        };
        System.out.println(predicate.test("我是Predicate-->匿名内部类实现"));
        System.out.println(predicate.test("我是Predicate"));


        /// Lambda表达式写法
        Predicate<String> predicate1 = s -> !s.isEmpty();
        System.out.println(predicate1.test("我是Predicate-->Lambda表达式实现"));
    }
}
