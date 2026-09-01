package com.it.lambda_demo;

import java.util.function.Consumer;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/15 星期二
 */
public class Lambda_Consumer_Test {
    public static void main(String[] args) {
        /**
         *   Consumer 消费型接口
         */
        /// 匿名内部类写法
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("我是匿名内部类写法  我拿到了外面传入的参数 ---> " + o);
            }
        };
        consumer.accept("小飞鼠");

        /// Lambda 表达式写法
        /// 语法格式：( 参数列表 ) -> { 方法体; }
        Consumer consumer1 = (Object o) -> {
            System.out.println("我是Lambda表达式写法  我拿到了外面传入的参数 ---> " + o);
        };
        consumer1.accept("小飞棍");

    }
}
