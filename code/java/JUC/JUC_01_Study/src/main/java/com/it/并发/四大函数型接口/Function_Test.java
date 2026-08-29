package com.it.并发.四大函数型接口;

import java.util.function.Function;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 11:37
 */
public class Function_Test {
    public static void main(String[] args) {
        /// 函数型接口
        /// 1. 函数型接口：接收一个参数，返回一个结果
        System.out.println(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + "我是函数型接口";
            }
        }.apply("开始"));

        /// Lambda表达式写法
        Function<String, String> fun = (String s) -> {
            return s + "我是Lambda写法-->函数型接口";
        };
        System.out.println(fun.apply("开始"));
    }
}
