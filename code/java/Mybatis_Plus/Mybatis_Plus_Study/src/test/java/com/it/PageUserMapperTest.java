package com.it;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.mapper.PageUserMapper;
import com.it.mapper.UserMapper;
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
public class PageUserMapperTest {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PageUserMapper pageUserMapper;

	@Test
	public void testPageQuery() {
		//设置分页参数
		Page<User> page = new Page<>(1, 5);
		userMapper.selectPage(page, null);
		//获取分页数据
		List<User> list = page.getRecords();
		list.forEach(System.out::println);
		System.out.println("当前页：" + page.getCurrent());
		System.out.println("每页显示的条数：" + page.getSize());
		System.out.println("总记录数：" + page.getTotal());
		System.out.println("总页数：" + page.getPages());
		System.out.println("是否有上一页：" + page.hasPrevious());
		System.out.println("是否有下一页：" + page.hasNext());
	}


	@Test
	public void testSelectUserByAgeLimit() {
		//设置分页参数
		Page<User> page = new Page<>(1, 5);
		pageUserMapper.selectUserByAgeLimit(page, 50);
		//获取分页数据
		List<User> list = page.getRecords();
		list.forEach(System.out::println);
		System.out.println("当前页：" + page.getCurrent());
		System.out.println("每页显示的条数：" + page.getSize());
		System.out.println("总记录数：" + page.getTotal());
		System.out.println("总页数：" + page.getPages());
		System.out.println("是否有上一页：" + page.hasPrevious());
		System.out.println("是否有下一页：" + page.hasNext());
	}
}
