package com.it.并发.Stream流;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 20:18
 */
public class 中间方法 {
    public static void main(String[] args) {
        /**
         * filter 过滤操作
         */
        ArrayList<People> list = new ArrayList<>();
        list.add(new People("张三", "18", "男"));
        list.add(new People("李四", "19", "男"));
        list.add(new People("小红", "20", "女"));
        list.add(new People("小明", "21", "男"));
        list.add(new People("小明", "21", "男"));
        /// 筛选出年龄大于18岁的人
        System.out.println("filter--->筛选出年龄大于18岁的人");
        list.stream().filter(people -> Integer.parseInt(people.getAge()) > 18).forEach((s)->System.out.println(s));
        System.out.println();

        /**
         * limit / skip 分页,跳表 操作
         */
        /// 筛选出年龄大于18岁的人 且只要一个展示
        System.out.println("limit--->筛选出年龄大于18岁的人 且只要一个展示");
        list.stream()
                .filter(people -> Integer.parseInt(people.getAge()) > 18)
                .limit(1)
                .forEach((s)->System.out.println(s));
        System.out.println();

        /// 筛选出年龄大于18岁的人 且跳过一个展示
        System.out.println("skip--->筛选出年龄大于18岁的人 且跳过一个展示");
        list.stream()
                .filter(people -> Integer.parseInt(people.getAge()) > 18)
                .skip(1)
                .forEach((s)->System.out.println(s));
        System.out.println();

        /**
         * distinct 去重操作
         */
        System.out.println("distinct--->去重操作-->(小明)是重复的");
        list.stream().distinct().forEach((s)->System.out.println(s));
        System.out.println();

        /**
         * map 类型转换
         */
        System.out.println("map--->类型转换");
        list.stream().map(people -> Integer.parseInt(people.getAge())).forEach((s)->System.out.println(s));
    }


}
@Data
@AllArgsConstructor
@NoArgsConstructor
class People{
    private String name;
    private String age;
    private String sex;
}
