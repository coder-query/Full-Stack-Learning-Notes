package com.it.并发.四大函数型接口;

import java.util.function.Consumer;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 11:54
 */
public class Consumer_Test {
    public static void main(String[] args) {
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("传入的参数是-->" + s);
            }
        };
        consumer1.accept("帅宏");


        /// Lambda 表达式写法
        Consumer<String> consumer2 = (str)->{
            System.out.println("传入的参数是-->"+str);
        };
        consumer2.accept("帅宏-Lambda");
    }

}
