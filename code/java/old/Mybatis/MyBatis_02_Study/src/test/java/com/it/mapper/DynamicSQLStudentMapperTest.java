package com.it.mapper;

import com.it.pojo.Student;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public class DynamicSQLStudentMapperTest {

    private static SqlSession sqlSession = MybatisUtils.getSqlSession();

    private static void commit() {
        sqlSession.commit();
    }

    /**
     * if + where 标签
     */
    @Test
    public void testSelectStudentByIdAndName() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        Student student = dynamicSQLStudentMapper.selectStudentByIdAndName(2, "帅宏-coding");
        System.out.println(student);
    }

    /**
     * set 标签
     */
    @Test
    public void testUpdateStudentById() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        Student student = new Student();
        student.setStuId(1);
        student.setStuName("七颜阁");
        student.setStuAge(25);
        student.setStuClazz("22计科一班");
        int flag = dynamicSQLStudentMapper.updateStudentById(student);
        System.out.println("flag == " + flag);
        System.out.println(flag == 1 ? "数据修改成功...! " : "数据修改失败");
        commit();
    }

    /**
     * foreach 标签  ==== 根据id批量查询
     */
    @Test
    public void testSelectBatchStudentByIds() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<Student> studentList = dynamicSQLStudentMapper.selectBatchStudentByIds(ids);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    /**
     * foreach 标签  ==== 批量新增
     */
    @Test
    public void testInertBatchStudent() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("aa", 12, "bb"));
        studentList.add(new Student("cc", 12, "dd"));
        studentList.add(new Student("ee", 12, "ff"));
        int flag = dynamicSQLStudentMapper.insertBatchStudent(studentList);
        System.out.println("flag == " + flag);
        System.out.println(flag > 0 ? "数据批量新增成功...!" : "数据批量新增失败...!");
        commit();
    }


    /**
     * foreach 标签  ==== 根据id批量删除
     */
    @Test
    public void testDeleteBatchStudentByIds() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(11);
        ids.add(12);
        int flag = dynamicSQLStudentMapper.deleteBatchStudentByIds(ids);
        System.out.println("flag == " + flag);
        System.out.println(flag > 0 ? " 数据批量删除成功...!" : "数据批量删除失败...!");
        commit();
    }


    /**
     * foreach 标签  ==== 批量id修改学生姓名
     */
    @Test
    public void testUpdateBatchStudent() {
        DynamicSQLStudentMapper dynamicSQLStudentMapper = sqlSession.getMapper(DynamicSQLStudentMapper.class);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "小帅"));
        studentList.add(new Student(2, "帅宏-coding"));
        studentList.add(new Student(3, "宏图"));
        int flag = dynamicSQLStudentMapper.updateBatchStudent(studentList);
        System.out.println("flag == " + flag);
        System.out.println(flag == 1 ? " 数据批量修改姓名成功...!" : "数据批量修改姓名失败...!");
        commit();
    }


    @After
    public void closeSqlSession() {
        sqlSession.close();
    }
}
