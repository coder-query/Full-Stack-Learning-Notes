package com.shuai.A_创建流;

import java.util.*;
import java.util.stream.Stream;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 14:28
 */
public class CreateStreamDemo {
    public static void main(String[] args) {
        // 1. 集合方式创建流
        // 1.1 Collection对象.stream();  -- 单列集合
        ArrayList<Object> arrayList = new ArrayList<>();
        Stream<Object> stream1 = arrayList.stream();
        // 1.2 Map对象.stream();  -- 双列集合
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        Stream<Map.Entry<Object, Object>> stream = objectObjectHashMap.entrySet().stream();
        stream.forEach(System.out::println);
        Stream<Object> stream5 = objectObjectHashMap.keySet().stream();
        stream5.forEach(System.out::println);

        // 2. 数组方式创建流
        String[] arr = new String[]{"1", "2", "3"};
        Stream<String> stream2 = Arrays.stream(arr);

        // 3. 通过Stream.of()创建流
        Stream<String> stream3 = Stream.of("1", "2", "3");
        stream3.forEach(System.out::println);

        // 4. 通过Stream.iterate()创建流
        Stream.iterate(0, (x) -> x + 2).limit(10).forEach(System.out::println);

        // 5. 通过Stream.generate()创建流
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

        // 6. 通过Stream.empty()创建空流
        Stream<Object> stream4 = Stream.empty();
        stream4.forEach(System.out::println);

        // 7. 通过Stream.builder()创建流
        Stream<Object> build =
                Stream.builder().build();
        build.forEach(System.out::println);


    }
}
