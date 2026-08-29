package com.it.lambda_demo;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/15 星期二
 */
public class Lambda_Supplier_Test {
	public static void main(String[] args) {
		/**
		 *  Supplier  提供性接口
		 */
		/// 匿名内部类写法
		Supplier supplier = new Supplier() {
			@Override
			public Object get() {
				int i = new Random().nextInt(10);
				return "我是匿名内部类写法   提供数据 ---> " + i;
			}
		};
		System.out.println(supplier.get());

		/// Lambda表达式写法
		/// 语法格式：( 参数列表 ) -> { 方法体; }
		Supplier supplier1 = () -> {
			int i = new Random().nextInt(10);
			return "我是Lambda表达式写法   提供数据 ---> " + i;
		};
		System.out.println(supplier1.get());

	}
}
