package com.it.config;

import com.it.pojo.ItUser;
import com.it.service.Impl.It_Annotation_ServiceImpl;
import com.other.config.OtherBeansConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:56
 */
@Configuration
@Import(OtherBeansConfiguration.class)
public class ItBeansConfiguration {
	@Bean
	public ItUser itUser() {
		return new ItUser();
	}

	@Bean
	public It_Annotation_ServiceImpl itAnnotationServiceImpl() {
		return new It_Annotation_ServiceImpl();
	}
}
