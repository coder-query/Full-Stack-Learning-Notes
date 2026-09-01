package com.it;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.it.mapper.PageUserMapper;
import com.it.mapper.QueryWrapperMapper;
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
public class QueryWrapperMapperTest {
	@Autowired
	QueryWrapperMapper queryWrapperMapper;
	@Autowired
	private PageUserMapper pageUserMapper;

	@Test
	public void testQueryWrapperMapper_01() {
		//查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper
				.like("name", "a")
				.between("age", 20, 30)
				.isNotNull("email");
		List<User> userList = queryWrapperMapper.selectList(userQueryWrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void testQueryWrapperMapper_02() {
		//按年龄降序查询用户，如果年龄相同则按id升序排列
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper
				.orderByAsc("age")
				.orderByAsc("id");
		List<User> userList = queryWrapperMapper.selectList(userQueryWrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void testQueryWrapperMapper_03() {
		//删除email为空的用户
		//DELETE FROM t_user WHERE (email IS NULL)
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper
				.isNull("email");
		int i = queryWrapperMapper.delete(userQueryWrapper);
		System.out.println("i = " + i);
	}

	@Test
	public void testQueryWrapperMapper_04() {
		//查询用户信息的username和age字段
		//SELECT username,age FROM t_user
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper
				.select("name", "age");
		List<User> userList = queryWrapperMapper.selectList(userQueryWrapper);
		userList.forEach(System.out::println);
	}

	@Test
	public void testQueryWrapperMapper_05() {
		String name = "Shing On Na";
		int age = 18;

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		//判断条件拼接
		//当name不为null拼接等于, age > 1 拼接等于判断
		//方案1: 手动判断
//		if (!StringUtils.isEmpty(name)) {
//			queryWrapper.eq("name", name);
//		}
//		if (age > 1) {
//			queryWrapper.eq("age", age);
//		}

		//方案2: 拼接condition判断
		//每个条件拼接方法都condition参数,这是一个比较运算,为true追加当前条件!
		//eq(condition,列名,值)
		queryWrapper.eq(!StringUtils.isEmpty(name), "name", name)
				.eq(age > 1, "age", age);
		List<User> userList = queryWrapperMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}


}
