package com.it.config;

import com.it.pojo.User;
import com.other.config.OtherBeansConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 21:50
 */
//@Configuration
//@ComponentScan("com.it")
//@Import(OtherBeansConfig.class)
public class ItBeansConfig {
	//	@Bean
	public User getUser() {
		return new User();
	}
}
