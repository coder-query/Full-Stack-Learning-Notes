package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 13:06
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	

	@RequestMapping("/test")
	public String test() {
		String value = redisTemplate.opsForValue().get("name");
		System.out.println(value);
		return value;
	}
}
