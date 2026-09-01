package com.mayikt.stream;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @ClassName Test10
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test10 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(10, 20, 30);
        Optional<Integer> reduce = integerStream.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a1, Integer a2) {
                return a1 + a2;
            }
        });
        System.out.println(reduce.get());
    }
}
