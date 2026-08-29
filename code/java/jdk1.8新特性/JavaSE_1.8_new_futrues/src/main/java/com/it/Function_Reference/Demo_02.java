package com.it.Function_Reference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/6 0006
 */
import java.util.function.Supplier;

/** 类名:静态方法 */
public class Demo_02 {
  public static void main(String[] args) {

    /** Lambda表达式写法 */
    Supplier<Long> supplier_01 =
        () -> {
          return System.currentTimeMillis();
        };
    System.out.println(supplier_01.get());

    /** 方法引用写法 */
    Supplier<Long> supplier_02 = System::currentTimeMillis;
    System.out.println(supplier_02.get());
  }
}
