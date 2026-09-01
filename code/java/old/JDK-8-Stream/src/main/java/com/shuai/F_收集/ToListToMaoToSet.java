package com.shuai.F_收集;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 15:10
 */
public class ToListToMaoToSet {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();

        // 获取所有学生的名字
        List<String> names = fixed30Students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("所有学生的名字：" + names);

        // 获取所有学生的姓名set集合去重
        Set<String> collect = fixed30Students.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());
        System.out.println("所有学生的姓名set集合去重：" + collect);

        // 获取对应学生的最高分科目和分数，以map形式展示
        Map<String, Map<String, Double>> collect1 = fixed30Students.stream()
                .collect(Collectors.toMap(
                        Student::getName,
                        student -> {
                            Map<String, Double> scores = student.getScores();
                            scores.entrySet().stream()
                                    .max(Comparator.comparing(Map.Entry::getValue))
                                    .ifPresent(entry -> {
                                        scores.clear();
                                        scores.put(entry.getKey(), entry.getValue());
                                    });
                            return scores;
                        },
                        (oldValue, newValue) -> oldValue
                ));

        System.out.println("-------------------------------------------");
        System.out.println("对应学生最高分科目和分数：" + collect1);


    }
}
