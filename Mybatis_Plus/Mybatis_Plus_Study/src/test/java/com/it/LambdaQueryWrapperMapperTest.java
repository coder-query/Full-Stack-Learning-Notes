package com.it;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.it.mapper.LambdaQueryWrapperMapper;
import com.it.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
@SpringBootTest
public class LambdaQueryWrapperMapperTest {
	@Autowired
	private LambdaQueryWrapperMapper lambdaQueryWrapperMapper;

	@Test
	public void testLambdaQueryWrapperMapper_01() {
		//查询用户名包含a，年龄在60到100之间，并且邮箱不为null的用户信息
		LambdaQueryWrapper<User> userLambdaQueryWrapper
				= new LambdaQueryWrapper<>();

		userLambdaQueryWrapper
				.like(User::getName, "a")
				.between(User::getAge, 60, 100)
				.isNotNull(User::getEmail);

		List<User> userList =
				lambdaQueryWrapperMapper.selectList(userLambdaQueryWrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void testLambdaQueryWrapperMapper_02() {
		//按年龄降序查询用户，如果年龄相同则按id升序排列
		LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
		userLambdaQueryWrapper
				.orderByAsc(User::getName)
				.orderByAsc(User::getId);
		List<User> userList = lambdaQueryWrapperMapper.selectList(userLambdaQueryWrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void testLambdaQueryWrapperMapper_03() {
		//查询用户信息的username和age字段
		//SELECT username,age FROM t_user
		LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
		userLambdaQueryWrapper
				.select(User::getAge, User::getName);
		List<User> userList = lambdaQueryWrapperMapper.selectList(userLambdaQueryWrapper);
		userList.forEach(System.out::println);
	}


}
