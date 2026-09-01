package com.shuai.E_规约;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 15:05
 */
public class ReduceDemo {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();
        String name = fixed30Students.stream()
                .map(Student::getName)
                .reduce((name1, name2) -> name1 + "|" + name2)
                .orElse( "");
        System.out.println("所有学生的姓名是：" + name);

        // 求语文分数总和
        Double sum = fixed30Students.stream()
                .map(Student::getScores)
                .map(scores -> scores.getOrDefault("语文",0.0))
                .reduce(0.0, Double::sum);
        System.out.println("语文分数总和是：" + sum);
    }
}
