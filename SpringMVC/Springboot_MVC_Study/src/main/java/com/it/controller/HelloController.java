package com.it.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/16 星期三
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("/mvc")
	public String helloMVC() {
		return "Hello MVC";
	}
}
