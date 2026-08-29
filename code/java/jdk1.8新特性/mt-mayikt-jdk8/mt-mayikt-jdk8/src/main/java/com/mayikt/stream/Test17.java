package com.mayikt.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 * @ClassName Test17
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test17 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        LongStream longStream = LongStream.rangeClosed(0, 50000000000L);
        // 使用并行流 计算  五百亿求和耗费的时间为: 7324 7850 7
        OptionalLong result = longStream.parallel().reduce((left, right) -> left + right);
        //使用多线程 处理 大的任务拆分成n多小的任务 在计算 在将结果合并 fork join框架
        System.out.println(result.getAsLong());
        Instant end = Instant.now();
        System.out.println("五百亿求和耗费的时间为: " + Duration.between(start, end).toMillis());
    }
}
