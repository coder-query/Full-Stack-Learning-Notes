package com.shuai.G_统计;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-10 15:11
 */
public class AllStaicData {
    public static void main(String[] args) {
        List<Student> fixed30Students = FixedStudentDataGenerator.getFixed30Students();
        System.out.println("班级总人数是：" + fixed30Students.size());

        DoubleSummaryStatistics 语文 = fixed30Students.stream()
                .collect(Collectors.summarizingDouble(s -> s.getScores().getOrDefault("语文", 0.0)));
        System.out.println("所有静态数据 + " + 语文);
    }

}
