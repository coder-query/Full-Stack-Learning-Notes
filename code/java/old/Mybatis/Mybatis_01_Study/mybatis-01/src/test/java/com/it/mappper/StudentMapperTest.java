package com.it.mappper;

import com.it.mapper.StudentMapper;

import com.it.pojo.Student_01;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentMapperTest {

//    <!--    查询所有学生信息-->
    @Test
    public void getStudentList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student_01> studentList = studentMapper.getStudentList();
        for (Student_01 student : studentList) {
            System.out.println(student);
        }
        if (sqlSession != null) sqlSession.close();
    }


//    <!--    查询学生加学生的老师姓名-->
    @Test
    public void getStudentWithTeacherList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student_01> studentList = studentMapper.getStudentWithTeacherList();
        for (Student_01 student : studentList) {
            System.out.println(student);
        }
        if (sqlSession != null) sqlSession.close();
    }
}
