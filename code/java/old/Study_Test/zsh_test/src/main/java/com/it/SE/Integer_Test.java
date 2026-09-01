package com.it.SE;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/25 星期二 11:15
 */
public class Integer_Test {
	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		Integer x = 129; ///高速缓存
		Integer y = Integer.valueOf(129);  /// 高速缓存

		System.out.println("a==b ---> " + (a == b));
		System.out.println("x==y ---> " + (x == y));
		System.out.println("a==y ---> " + (a == y));


		System.out.println("a.equals(b) ---> " + a.equals(b));
		System.out.println("x.equals(y) ---> " + x.equals(y));
		System.out.println("a.equals(y) ---> " + a.equals(y));
	}
}
