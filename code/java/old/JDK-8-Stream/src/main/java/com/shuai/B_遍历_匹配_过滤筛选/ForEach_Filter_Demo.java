package com.shuai.B_遍历_匹配_过滤筛选;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 14:42
 */
public class ForEach_Filter_Demo {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();

        // 找出姓张的
        List<Student> 张 = fixed30Students.stream()
                .filter(student -> {
                    return student.getName().startsWith("张");
                }).collect(Collectors.toList());
        System.out.println("姓张的：" + 张);

        // 判断这个班级的学生是否存在数学分数90以上的
        boolean anyMatch = fixed30Students.stream()
                .anyMatch(stu -> {
                    Map<String, Double> scores = stu.getScores();
                    Double 数学 = scores.get("数学");
                    return 数学 >= 90;
                });
        System.out.println("这个班级的学生是否存在数学分数90以上的：" + anyMatch);

        // 过滤出学生的语文成绩在90以上的
        List<Student> 语文90以上的 = fixed30Students.stream()
                .filter(stu -> {
                    Map<String, Double> scores = stu.getScores();
                    Double 语文 = scores.getOrDefault("语文",0.0);
                    return 语文 >= 90;
                })
                .collect(Collectors.toList());
        System.out.println("过滤出学生的语文成绩在90以上的：" + 语文90以上的);
    }
}
