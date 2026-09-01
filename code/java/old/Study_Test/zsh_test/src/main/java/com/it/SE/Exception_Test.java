package com.it.SE;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/25 星期二 16:43
 */
public class Exception_Test {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<String> arrayList = new ArrayList<>();

		/// 单线程
		arrayList.add(UUID.randomUUID().toString());
		arrayList.add(UUID.randomUUID().toString());
		arrayList.add(UUID.randomUUID().toString());
		arrayList.add(UUID.randomUUID().toString());
		arrayList.add(UUID.randomUUID().toString());


		///打印
		for (String s : arrayList) {
			System.out.println(s);
		}

//        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

		/// 多线程
//        for (int i = 1; i <= 30; i++) {
//            new Thread(()->{
//                    copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0,5));
//                System.out.println(copyOnWriteArrayList);
//            },String.valueOf(i)).start();
//        }

		for (int i = 1; i <= 30; i++) { /// 循环30次 --> 30个线程</=>
			new Thread(() -> {
				arrayList.add(UUID.randomUUID().toString().substring(0, 5));
				System.out.println(arrayList);
			}, String.valueOf(i)).start();
		}

	}
}
