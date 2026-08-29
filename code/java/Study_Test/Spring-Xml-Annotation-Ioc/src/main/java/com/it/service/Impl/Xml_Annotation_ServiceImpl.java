package com.it.service.Impl;

import com.it.service.Xml_Annotation_Service;
import org.springframework.stereotype.Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:51
 * //
 */

@Service
public class Xml_Annotation_ServiceImpl implements Xml_Annotation_Service {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello 靓仔 ---> " + name);
	}
}
