package com.mayikt;

import com.mayikt.service.MayiktService;
import com.mayikt.service.MessageInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @ClassName Test23
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test23 {
    public static void main(String[] args) {
        // 1.使用匿名内部类的形式
//        MayiktService mayiktService = new MayiktService() {
//            @Override
//            public String get(Test23 test23) {
//                return test23.objGet();
//            }
//        };
//        System.out.println(mayiktService.get(new Test23()));
        // 2.Lambda
//        MayiktService mayiktService = (test23) -> test23.objGet();
//        System.out.println(mayiktService.get(new Test23()));
        // 3.方法引入 在这时候我们函数接口 第一个参数传递test23 返回调用test23.objGet方法
//        MayiktService mayiktService = Test23::objGet;
//        System.out.println(mayiktService.get(new Test23()));
        //Test23::objGet;----- (test23) -> test23.objGet();
        //   R apply(T t); T  apply方法传递的参数类型 ： R apply 方法返回的类型
        // 需要将string类型字符串获取长度
//        Function<String, Integer> strFunction = (str) -> {
//            return str.length();
//        };
        Function<String, Integer> function2 = String::length;
        System.out.println(function2.apply("mayikt"));


    }

    public String objGet() {
        return "mayikt";
    }


}
