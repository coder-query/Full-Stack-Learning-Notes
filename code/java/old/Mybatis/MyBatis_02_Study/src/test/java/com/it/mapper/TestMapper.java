package com.it.mapper;

import com.it.pojo.Student;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
public class TestMapper {
	private static SqlSession sqlSession = MybatisUtils.getSqlSession();

	private static void commit() {
		sqlSession.commit();
	}


	@Test
	public void test() {
		Student student = sqlSession.selectOne("zsh.xx.ynn", 1);
		System.out.println(student);
	}


	@After
	public void closeSqlSession() {
		sqlSession.close();
	}
}
