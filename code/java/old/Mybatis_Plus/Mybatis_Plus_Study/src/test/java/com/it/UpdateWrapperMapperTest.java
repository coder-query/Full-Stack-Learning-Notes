package com.it;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.it.mapper.UpdateWrapperMapper;
import com.it.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
@SpringBootTest
public class UpdateWrapperMapperTest {
	@Autowired
	private UpdateWrapperMapper updateWrapperMapper;

	@Test
	public void testUpdateWrapperMapper_01() {
		//将年龄大于20并且用户名中包含有a或邮箱为null的用户信息修改
		//UPDATE t_user SET age=?, email=? WHERE username LIKE ? AND age > ? OR email IS NULL)
		UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
		userUpdateWrapper.gt("age", 20)
				.like("name", "a")
				.or()
				.isNull("email")
				.set("email", null)  // set 指定列和结果
				.set("age", 18);
		int flag = updateWrapperMapper.update(userUpdateWrapper);
		System.out.println("flag = " + flag);
	}


}
