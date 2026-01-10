package com.shuai.H_分组;

import com.shuai.model.FixedStudentDataGenerator;
import com.shuai.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class DownstreamCollectorDemo {
    public static void main(String[] args) {
        List<Student> students = FixedStudentDataGenerator.getFixed30Students();

        // ==================== 场景1：Value改为“计数（Long）” ====================
        // 按“科目数量”分组，Value是每组的学生人数（替代List<T>）
        Map<Integer, Long> countBySubjectNum = students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getScores().size(), // Key：科目数量（3/4）
                        Collectors.counting() // 下游收集器：计数
                ));
        System.out.println("1. 按科目数量分组（Value=人数）：");
        countBySubjectNum.forEach((num, count) -> 
                System.out.println("   科目数" + num + "：" + count + "人"));

        // ==================== 场景2：Value改为“单个对象（T）” ====================
        // 按“平均分区间”分组，Value是每组平均分最高的学生（不再是List）
        Map<String, Student> topStudentByAvgRange = students.stream()
                .collect(Collectors.groupingBy(
                        s -> {
                            double avg = s.calculateAverage();
                            return avg >= 90 ? "90+" : (avg >= 80 ? "80-89" : "<80");
                        },
                        // 下游收集器：先找最大值，再提取Optional中的值
                        Collectors.collectingAndThen(
                                Collectors.maxBy((s1, s2) -> Double.compare(s1.calculateAverage(), s2.calculateAverage())),
                                opt -> opt.orElse(null)
                        )
                ));
        System.out.println("\n2. 按平均分区间分组（Value=每组最高分学生）：");
        topStudentByAvgRange.forEach((range, student) -> 
                System.out.println("   " + range + "：" + student.getName() + "（平均分：" + String.format("%.2f", student.calculateAverage()) + "）"));

        // ==================== 场景3：Value改为“Set<T>” ====================
        // 按“是否有数学成绩”分区，Value是学生姓名的Set（去重，避免List）
        Map<Boolean, Set<String>> nameSetByMathScore = students.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.getScores().containsKey("数学"), // Key：是否有数学成绩
                        Collectors.mapping(Student::getName, Collectors.toSet()) // 下游：映射姓名+转Set
                ));
        System.out.println("\n3. 按是否有数学成绩分区（Value=姓名Set）：");
        System.out.println("   有数学成绩的学生姓名：" + nameSetByMathScore.get(true));
        System.out.println("   无数学成绩的学生姓名：" + nameSetByMathScore.get(false));

        // ==================== 场景4：Value改为“数值汇总（DoubleSummaryStatistics）” ====================
        // 按“科目数量”分组，Value是每组平均分的统计信息（总和、均值、最值）
        Map<Integer, DoubleSummaryStatistics> statsBySubjectNum = students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getScores().size(),
                        Collectors.summarizingDouble(Student::calculateAverage) // 下游：汇总统计
                ));
        System.out.println("\n4. 按科目数量分组（Value=平均分统计信息）：");
        statsBySubjectNum.forEach((num, stats) -> {
            System.out.println("   科目数" + num + "：");
            System.out.println("      平均分总和：" + String.format("%.2f", stats.getSum()));
            System.out.println("      平均分均值：" + String.format("%.2f", stats.getAverage()));
            System.out.println("      最高分：" + String.format("%.2f", stats.getMax()));
            System.out.println("      最低分：" + String.format("%.2f", stats.getMin()));
        });

        // ==================== 场景5：Value改为“字符串拼接” ====================
        // 按“平均分≥85”分区，Value是每组学生姓名的拼接字符串
        Map<Boolean, String> nameStrByAvg = students.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.calculateAverage() >= 85,
                        Collectors.mapping(Student::getName, Collectors.joining("、")) // 下游：拼接姓名
                ));
        System.out.println("\n5. 按平均分≥85分区（Value=姓名拼接字符串）：");
        System.out.println("   平均分≥85的学生：" + nameStrByAvg.get(true));
        System.out.println("   平均分<85的学生：" + nameStrByAvg.get(false));

//        // ==================== 场景6：Value改为“自定义类型（比如平均分总和）” ====================
//        // 按“是否有英语成绩”分组，Value是每组的英语成绩总和
//        Map<Boolean, Double> totalEnglishScore = students.stream()
//                .collect(Collectors.partitioningBy(
//                        s -> s.getScores().containsKey("英语"),
//                        // 下游：过滤有英语成绩的学生，求和
//                        Collectors.filtering(
//                                s -> s.getScores().containsKey("英语"),
//                                Collectors.summingDouble(s -> s.getScores().get("英语"))
//                        )
//                ));
//        System.out.println("\n6. 按是否有英语成绩分区（Value=英语成绩总和）：");
//        System.out.println("   有英语成绩的学生总分：" + String.format("%.2f", totalEnglishScore.get(true)));
//        System.out.println("   无英语成绩的学生总分：" + totalEnglishScore.get(false)); // 0.0
    }
}
