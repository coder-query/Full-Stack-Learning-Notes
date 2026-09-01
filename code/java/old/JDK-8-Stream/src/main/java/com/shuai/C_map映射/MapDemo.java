package com.shuai.C_map映射;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 14:53
 */
public class MapDemo {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();

        // 提取出这个班级的学生姓名
        List<String> collect = fixed30Students.stream()
                .map(Student::getName).collect(Collectors.toList());
        System.out.println("这个班级的学生姓名：" + collect);

    }
}
