package com.other.config;

import com.other.pojo.OtherUser;
import com.other.service.Impl.Other_Annotation_ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:59
 */
@Configuration
public class OtherBeansConfiguration {
	@Bean
	public OtherUser otherUser() {
		return new OtherUser();
	}

	@Bean
	public Other_Annotation_ServiceImpl otherAnnotationServiceImpl() {
		return new Other_Annotation_ServiceImpl();
	}
}
