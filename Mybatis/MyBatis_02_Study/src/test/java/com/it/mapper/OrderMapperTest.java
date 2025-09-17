package com.it.mapper;

import com.it.pojo.Customer;
import com.it.pojo.Order;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public class OrderMapperTest {

	private static SqlSession sqlSession = MybatisUtils.getSqlSession();

	private static void commit() {
		sqlSession.commit();
	}

	@Test
	public void testSelectOrderWithCustomer() {
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		Order order = orderMapper.selectOrderWithCustomer(1);
		System.out.println(order);
	}


	@After
	public void closeSqlSession() {
		sqlSession.close();
	}
}
