package com.it.mapper;

import com.it.pojo.Student_01;

import java.util.List;

public interface StudentMapper {

    // 获取所有学生信息
    List<Student_01> getStudentList();

    // 获取所有学生信息，并且查询每个学生的老师
    List<Student_01> getStudentWithTeacherList();
}
