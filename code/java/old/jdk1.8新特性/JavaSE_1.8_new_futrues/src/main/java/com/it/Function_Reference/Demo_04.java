package com.it.Function_Reference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/7 0007
 */
import com.pojo.User;

import java.util.function.Supplier;

/** 类名::构造器 (类名::new) */
public class Demo_04 {
  public static void main(String[] args) {
    /** Lambda表达式写法 */
    Supplier<User> supplier =
        () -> {
          return new User();
        };
    System.out.println(supplier.get());
    /** 方法引用写法 */
    Supplier<User> supplier1 = User::new;
    System.out.println(supplier1.get());
  }
}
