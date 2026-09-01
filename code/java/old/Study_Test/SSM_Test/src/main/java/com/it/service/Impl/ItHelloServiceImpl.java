package com.it.service.Impl;

import com.it.service.ItHelloService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 11:29
 */

@Service
public class ItHelloServiceImpl implements ItHelloService {
	@Override
	public String sayHello() {
		return "It_Hello";
	}
}
