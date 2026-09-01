package com.it.mappper;

import com.it.mapper.StudentMapper;
import com.it.mapper.TeacherMapper;
import com.it.pojo.Student_01;
import com.it.pojo.Teacher_01;
import com.it.pojo.Teacher_02;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TeacherMapperTest {

    //查询所有老师
    @Test
    public void getTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher_02> teacherList = teacherMapper.getTeacher();
        for (Teacher_02 teacher : teacherList) {
            System.out.println(teacher);
        }
        if (sqlSession != null) sqlSession.close();

    }

    //根据id查询老师以及老师的所带学生
    @Test
    public void getTeacherWithStudentById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher_02 teacherWithStudentById = teacherMapper.getTeacherWithStudentById(1);
        System.out.println(teacherWithStudentById);
        if (sqlSession != null) sqlSession.close();

    }
}
