package com.it.mapper;

import com.it.pojo.Teacher_02;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    //查询所有老师
   List<Teacher_02> getTeacher();

    //根据id查询老师以及老师的所带学生
    Teacher_02 getTeacherWithStudentById(@Param("tid") int id);

}
