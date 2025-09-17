package com.it.service.Impl;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import com.it.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 20:20
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}
}
