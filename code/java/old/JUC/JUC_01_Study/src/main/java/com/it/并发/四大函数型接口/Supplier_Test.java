package com.it.并发.四大函数型接口;

import java.util.function.Supplier;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 11:59
 */
public class Supplier_Test {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        };
        System.out.println(supplier.get());

        /// Lambda表达式写法
        Supplier<String> supplier1 = () -> "hello";
        System.out.println(supplier1.get());

    }
}
