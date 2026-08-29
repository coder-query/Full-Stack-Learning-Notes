package com.shuai.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生类，封装学生基本信息和成绩
 */
public class Student {
    private String name;          // 学生姓名
    private String studentId;     // 学号
    private Map<String, Double> scores;  // 各科成绩（科目-分数）

    // 构造方法
    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.scores = new HashMap<>();
    }

    // 添加/更新科目成绩（带合法性校验）
    public void addScore(String subject, double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("成绩必须在0-100之间，当前输入：" + score);
        }
        scores.put(subject, score);
    }


    // Getter方法（便于外部获取数据）
    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public Map<String, Double> getScores() {
        return new HashMap<>(scores); // 返回副本，避免外部修改
    }

    // 计算平均分
    public double calculateAverage() {
        if (scores.isEmpty()) return 0.0;
        double total = 0.0;
        for (double score : scores.values()) {
            total += score;
        }
        return total / scores.size();
    }

    // 重写toString，便于打印学生信息
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", scores=" + scores +
                '}';
    }
}
