package com.shuai.D_聚合;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 14:56
 */
public class Max_Min_Count_Demo {
    public static void main(String[] args) {

        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();

        // 遍历出所有学生
        fixed30Students.forEach(System.out::println);
        // 找出语文成绩最高的
        Student student = fixed30Students.stream()
                .filter(Objects::nonNull)
                .max((stu1, stu2) -> {
                    Map<String, Double> scores1 = stu1.getScores();
                    Map<String, Double> scores2 = stu2.getScores();
                    Double 语文1 = scores1.getOrDefault("语文", 0.0);
                    Double 语文2 = scores2.getOrDefault("语文", 0.0);
                    return 语文1.compareTo(语文2);
                }).get();
        System.out.println("找出语文成绩最高的：" + student);

        // 找出英语成绩最低的
        Student student1 = fixed30Students.stream()
                .filter(Objects::nonNull)
                .min((stu1, stu2) -> {
                    Map<String, Double> scores1 = stu1.getScores();
                    Map<String, Double> scores2 = stu2.getScores();
                    Double 英语1 = scores1.getOrDefault("英语", 0.0);
                    Double 英语2 = scores2.getOrDefault("英语", 0.0);
                    return 英语1.compareTo(英语2);
                }).get();
        System.out.println("找出英语成绩最低的：" + student1);
        // 统计这个班级的学生英语及格的人数，如果这个学生没有英语成绩则是0分
        long count = fixed30Students.stream()
                .filter(Objects::nonNull)
                .filter(stu -> {
                    return stu.getScores().getOrDefault("英语", 0.0) >= 60;
                }).count();
        System.out.println("这个班级的学生英语及格人数为：" + count);
    }
}
