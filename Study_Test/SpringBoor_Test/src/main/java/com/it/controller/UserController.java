package com.it.controller;

import com.it.pojo.User;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 20:17
 */
@RestController
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/user/{id}")
	public String getUserByName(@PathVariable Integer id) {
		return userService.getUserById(id).toString();
	}

}
