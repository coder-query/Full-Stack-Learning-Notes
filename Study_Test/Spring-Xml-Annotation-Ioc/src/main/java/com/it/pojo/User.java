package com.it.pojo;

import com.it.service.Impl.Xml_Annotation_ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
	@Autowired
	private Xml_Annotation_ServiceImpl xml_annotation_service;
	private String name;
	private int age;
	private String gender;
}
