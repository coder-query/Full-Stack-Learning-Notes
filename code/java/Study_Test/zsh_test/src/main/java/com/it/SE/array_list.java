package com.it.SE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/25 星期二 12:21
 */
public class array_list {
	public static void main(String[] args) {

		/// 数组转list --> asList
		String[] arr = {"1", "2", "3"};
		List<String> list1 = Arrays.asList(arr); /// 包装一层ArrayList  地址相同


		/// 修改list  --> arr也变化
		list1.set(0, "4");
//        list1.add("5");  /// 会出现UnsupportedOperationException异常
		System.out.println(list1);
		System.out.println(Arrays.toString(arr));


		/// list转数组  --> toArray
		ArrayList list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("2");
		list2.add("3");

		String[] array = (String[]) list2.toArray(new String[list2.size()]);  /// 赋值 不同地址

		array[0] = "4";
		System.out.println(list2);
		System.out.println(Arrays.toString(array));


	}
}
