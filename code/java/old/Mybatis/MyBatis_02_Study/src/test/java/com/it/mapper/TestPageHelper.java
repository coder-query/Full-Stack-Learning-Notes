package com.it.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.pojo.Student;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
public class TestPageHelper {
	private static SqlSession sqlSession = MybatisUtils.getSqlSession();

	private static void commit() {
		sqlSession.commit();
	}

	@Test
	public void testGetAllStudent() {
		TestPageHelperMapper pageInfo = sqlSession.getMapper(TestPageHelperMapper.class);

		PageHelper.startPage(1, 3);
		List<Student> studentList = pageInfo.getAllStudent();

		PageInfo<Student> pageInfo1 = new PageInfo<>(studentList);

		long total = pageInfo1.getTotal(); // 获取总记录数
		System.out.println("total = " + total);
		int pages = pageInfo1.getPages();  // 获取总页数
		System.out.println("pages = " + pages);
		int pageNum = pageInfo1.getPageNum(); // 获取当前页码
		System.out.println("pageNum = " + pageNum);
		int pageSize = pageInfo1.getPageSize(); // 获取每页显示记录数
		System.out.println("pageSize = " + pageSize);
		List<Student> students = pageInfo1.getList(); //获取查询页的数据集合
		System.out.println("students = " + students);
		students.forEach(System.out::println);
	}


	@After
	public void closeSqlSession() {
		sqlSession.close();
	}
}
