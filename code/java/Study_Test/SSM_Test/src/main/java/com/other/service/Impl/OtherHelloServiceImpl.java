package com.other.service.Impl;

import com.other.service.OtherHelloService;
import org.springframework.stereotype.Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 12:50
 */

@Service
public class OtherHelloServiceImpl implements OtherHelloService {
	@Override
	public String sayHello() {
		return "Other_Hello";
	}
}
