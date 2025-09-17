package com.other.service.Impl;

import com.it.service.It_Annotation_Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:58
 */
public class Other_Annotation_ServiceImpl implements It_Annotation_Service {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello 靓仔 ---> " + name);
	}
}
