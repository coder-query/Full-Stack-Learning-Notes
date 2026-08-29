package com.it.Function_Reference;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/7 0007
 */
import java.util.function.Function;

/** 数组::构造器 (数组::new) */
public class Demo_05 {
  public static void main(String[] args) {
    /** Lambda表达式写法 */
    Function<Integer, String[]> function =
        (count) -> {
          return new String[count];
        };
    String[] arr = function.apply(5);
    System.out.println(arr.length);
    /** 方法引用写法 */
    Function<Integer, String[]> function_02 = String[]::new;
    String[] arr_02 = function_02.apply(5);
    System.out.println(arr_02.length);
  }
}
