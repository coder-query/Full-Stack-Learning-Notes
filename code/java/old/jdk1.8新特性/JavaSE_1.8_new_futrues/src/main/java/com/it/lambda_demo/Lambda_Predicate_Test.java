package com.it.lambda_demo;

import java.util.function.Predicate;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/15 星期二
 */
public class Lambda_Predicate_Test {
	public static void main(String[] args) {
		/**
		 *   Predicate 断言型接口
		 */
		Predicate predicate = new Predicate() {
			@Override
			public boolean test(Object o) {
				if (o instanceof Integer){
					if (((Integer)o).intValue() > 10){
						return true;
					}
				}
				return false;
			}
		};
		System.out.println(predicate.test(15));

		Predicate predicate1 = o -> {
			if (o instanceof Integer){
				if (((Integer)o).intValue() > 10){
					return true;
				}
			}
			return false;
		};

		System.out.println(predicate.test(5));
	}
}
