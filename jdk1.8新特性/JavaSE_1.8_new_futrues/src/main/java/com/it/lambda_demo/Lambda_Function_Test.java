package com.it.lambda_demo;

import java.util.function.Function;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/15 星期二
 */
public class Lambda_Function_Test {
	public static void main(String[] args) {
		/**
		 *   Function 函数型接口
		 */
		Function function = new Function<Integer, String>() {
			@Override
			public String apply(Integer integer) {
				int temp = integer;
				integer = integer + 1;
				return "我是匿名内部类写法  传入值为" + temp + " 修改后为 " + integer;
			}
		};
		System.out.println(function.apply(1));


		Function function1 = (i) ->{
			return "qwder";
		};
		System.out.println(function1.apply(2));
	}
}
