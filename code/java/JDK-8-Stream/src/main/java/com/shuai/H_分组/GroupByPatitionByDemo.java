package com.shuai.H_分组;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 15:15
 */
public class GroupByPatitionByDemo {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();
        Map<String, List<Student>> groupByAvgRange = fixed30Students.stream()
                .collect(Collectors.groupingBy(s -> {
                    double avg = s.calculateAverage();
                    if (avg >= 90) return "90分及以上";
                    else if (avg >= 80) return "80-89分";
                    else return "80分以下";
                }));
        System.out.println("分组：" + groupByAvgRange);
        // 遍历打印各分组
        groupByAvgRange.forEach((range, list) ->
                System.out.println("1. " + range + "：" + list.size() + "人"));

    }
}
