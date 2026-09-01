package com.it.Function_Reference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/6
 */
import java.util.Date;
import java.util.function.Supplier;

/** 对象:方法 */
public class Demo_01 {
  public static void main(String[] args) {
    Date date_01 = new Date();
    /** Lambda写法 */
    Supplier<Long> supplier_01 =
        () -> {
          return date_01.getTime();
        };
    /** 方法引用写法 */
    Supplier<Long> supplier_02 = date_01::getTime;
    System.out.println(supplier_01.get());
    System.out.println(supplier_02.get());
  }
}
