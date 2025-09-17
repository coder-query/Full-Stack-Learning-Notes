package com.it;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
@SpringBootTest
public class UserMapperTest {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 单条数据新增
	 */
	@Test
	public void testInsert() {
		User user = new User();
		user.setName("test_Insert_帅宏");
		user.setEmail("2798679648@qq.com");
		user.setAge(21);
		int flag = userMapper.insert(user);
		System.out.println("flag = " + flag);
	}

	/**
	 * 删除
	 */
	@Test
	public void testDelete() {
		// 根据id删除
		int flag = userMapper.deleteById(1911678938150580225L);
		System.out.println("flag = " + flag);

		// 根据某个字段删除
		// 建一个map集合
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("age", 20);
		int i = userMapper.deleteByMap(hashMap);
		System.out.println("i = " + i);

		// 批量删除
		List<Long> ids = new ArrayList<>();
		ids.add(4L);
		ids.add(5L);
		int i1 = userMapper.deleteBatchIds(ids);
		System.out.println("i1 = " + i1);
	}

	/**
	 * 修改数据
	 */
	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(1L);
		user.setAge(100);
		int i = userMapper.updateById(user);
		System.out.println("i = " + i);
	}

	/**
	 * 查询数据
	 */
	@Test
	public void testSelect() {
		// 根据id查询user
		User user = userMapper.selectById(1L);
		System.out.println("user = " + user);

		// 根据ids批量查询
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(3L);
		List<User> userList = userMapper.selectBatchIds(ids);
		for (User user1 : userList) {
			System.out.println("user1 = " + user1);
		}
	}
}
