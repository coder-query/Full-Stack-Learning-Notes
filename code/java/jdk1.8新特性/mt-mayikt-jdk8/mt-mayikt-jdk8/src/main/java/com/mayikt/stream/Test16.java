package com.mayikt.stream;

import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName Test16
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test16 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        // 单线程：五百亿求和花费的时间为: 16-18s 17894 17.8s
        System.out.println("五百亿求和花费的时间为: " + Duration.between(start, end).toMillis());

    }
}
