package com.it.并发.Stream流;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/20 星期四 0:03
 */
public class 收集数据 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三-男-21");
        list.add("李四-男-22");
        list.add("王五-女-23");
        list.add("赵六-男-24");
        list.add("孙七-女-25");

        /**
         * 过滤出男性,并收集数据转为单列集合
         */
        List<String> collect = list.stream()
                .filter(s -> s.split("-")[1].equals("男"))
                .collect(Collectors.toList());
        collect.forEach((s -> System.out.println(s)));


        /**
         * 过滤出女性,并收集数据转为双列集合
         */

        Map<String, String> collect1 = list.stream()
                .filter(s -> s.split("-")[1].equals("女"))
                .collect(Collectors.toMap(
                        k -> k.split("-")[0],
                        v -> v.split("-")[2]
                ));
        collect1.forEach((k, v) -> System.out.println(k + ":" + v));

    }
}
