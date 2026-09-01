package com.it.Function_Reference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/6 0006
 */
import java.util.function.Function;
import java.util.function.Supplier;

/** 类名::实例方法 */
public class Demo_03 {
  public static void main(String[] args) {

    String str = "hello world";
    /** Lambda表达式写法 */
    Function<String, String> function =
        (s) -> {
          return s.toUpperCase();
        };
    System.out.println(function.apply(str));
    /** 方法引用写法 */
    Function<String, String> function_02 = String::toUpperCase;
    System.out.println(function_02.apply(str));
  }
}
