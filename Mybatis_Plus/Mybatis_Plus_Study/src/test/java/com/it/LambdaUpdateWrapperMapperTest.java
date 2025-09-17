package com.it;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.it.mapper.LambdaUpdateWrapperMapper;
import com.it.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
@SpringBootTest
public class LambdaUpdateWrapperMapperTest {
	@Autowired
	private LambdaUpdateWrapperMapper lambdaUpdateWrapperMapper;

	//将年龄大于20并且用户名中包含有a或邮箱为null的用户信息修改
	//UPDATE t_user SET age=?, email=? WHERE username LIKE ? AND age > ? OR email IS NULL)
	@Test
	public void testLambdaUpdateWrapperMapper() {
		//将年龄大于20并且用户名中包含有a或邮箱为null的用户信息修改
		//UPDATE t_user SET age=?, email=? WHERE username LIKE ? AND age > ? OR email IS NULL)
		LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
		userLambdaUpdateWrapper
				.gt(User::getAge, "20")
				.like(User::getName, "a")
				.or()
				.isNull(User::getEmail);
		int i = lambdaUpdateWrapperMapper.update(userLambdaUpdateWrapper);
		System.out.println("i = " + i);
	}
}
