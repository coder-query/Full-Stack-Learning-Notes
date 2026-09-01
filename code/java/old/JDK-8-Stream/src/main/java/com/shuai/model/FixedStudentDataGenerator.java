package com.shuai.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成固定30条学生死数据的工具类（每次运行结果完全一致）
 */
public class FixedStudentDataGenerator {

    /**
     * 获取固定的30条学生数据（死数据）
     * @return 包含30个Student对象的列表
     */
    public static List<Student> getFixed30Students() {
        List<Student> studentList = new ArrayList<>();

        // ========== 第1-10条数据 ==========
        Student s1 = new Student("张三", "2026000001");
        s1.addScore("语文", 85.5);
        s1.addScore("数学", 92.0);
        s1.addScore("英语", 88.0);
        studentList.add(s1);

        Student s2 = new Student("李四", "2026000002");
        s2.addScore("语文", 78.0);
        s2.addScore("数学", 89.5);
        s2.addScore("物理", 91.0);
        s2.addScore("化学", 82.5);
        studentList.add(s2);

        Student s3 = new Student("王五", "2026000003");
        s3.addScore("数学", 76.5);
        s3.addScore("英语", 84.0);
        s3.addScore("生物", 88.5);
        studentList.add(s3);

        Student s4 = new Student("赵六", "2026000004");
        s4.addScore("语文", 90.0);
        s4.addScore("英语", 93.5);
        s4.addScore("历史", 87.0);
        s4.addScore("地理", 81.0);
        studentList.add(s4);

        Student s5 = new Student("孙七", "2026000005");
        s5.addScore("数学", 88.5);
        s5.addScore("物理", 79.0);
        s5.addScore("政治", 85.0);
        studentList.add(s5);

        Student s6 = new Student("周八", "2026000006");
        s6.addScore("语文", 82.0);
        s6.addScore("化学", 90.5);
        s6.addScore("历史", 77.5);
        studentList.add(s6);

        Student s7 = new Student("吴九", "2026000007");
        s7.addScore("英语", 86.0);
        s7.addScore("生物", 89.0);
        s7.addScore("地理", 83.5);
        s7.addScore("政治", 78.0);
        studentList.add(s7);

        Student s8 = new Student("郑十", "2026000008");
        s8.addScore("数学", 94.0);
        s8.addScore("物理", 91.5);
        s8.addScore("化学", 88.0);
        studentList.add(s8);

        Student s9 = new Student("钱十一", "2026000009");
        s9.addScore("语文", 75.5);
        s9.addScore("英语", 80.0);
        s9.addScore("历史", 89.5);
        studentList.add(s9);

        Student s10 = new Student("孙十二", "2026000010");
        s10.addScore("数学", 81.0);
        s10.addScore("生物", 85.5);
        s10.addScore("地理", 90.0);
        s10.addScore("政治", 82.5);
        studentList.add(s10);

        // ========== 第11-20条数据 ==========
        Student s11 = new Student("李十三", "2026000011");
        s11.addScore("语文", 89.0);
        s11.addScore("数学", 87.5);
        s11.addScore("英语", 91.0);
        studentList.add(s11);

        Student s12 = new Student("王十四", "2026000012");
        s12.addScore("物理", 84.5);
        s12.addScore("化学", 88.0);
        s12.addScore("生物", 86.5);
        studentList.add(s12);

        Student s13 = new Student("刘十五", "2026000013");
        s13.addScore("语文", 79.5);
        s13.addScore("历史", 92.0);
        s13.addScore("地理", 83.0);
        s13.addScore("政治", 87.5);
        studentList.add(s13);

        Student s14 = new Student("陈十六", "2026000014");
        s14.addScore("数学", 90.5);
        s14.addScore("英语", 85.0);
        s14.addScore("物理", 88.5);
        studentList.add(s14);

        Student s15 = new Student("杨十七", "2026000015");
        s15.addScore("语文", 86.5);
        s15.addScore("化学", 81.0);
        s15.addScore("历史", 84.0);
        studentList.add(s15);

        Student s16 = new Student("赵十八", "2026000016");
        s16.addScore("英语", 92.5);
        s16.addScore("生物", 78.5);
        s16.addScore("地理", 89.0);
        s16.addScore("政治", 80.5);
        studentList.add(s16);

        Student s17 = new Student("黄十九", "2026000017");
        s17.addScore("数学", 83.0);
        s17.addScore("物理", 87.5);
        s17.addScore("化学", 90.0);
        studentList.add(s17);

        Student s18 = new Student("周二十", "2026000018");
        s18.addScore("语文", 91.5);
        s18.addScore("英语", 88.5);
        s18.addScore("历史", 82.0);
        studentList.add(s18);

        Student s19 = new Student("吴二一", "2026000019");
        s19.addScore("数学", 77.0);
        s19.addScore("生物", 84.5);
        s19.addScore("政治", 86.0);
        studentList.add(s19);

        Student s20 = new Student("郑二二", "2026000020");
        s20.addScore("物理", 93.0);
        s20.addScore("化学", 85.5);
        s20.addScore("地理", 81.5);
        s20.addScore("历史", 88.0);
        studentList.add(s20);

        // ========== 第21-30条数据 ==========
        Student s21 = new Student("钱二三", "2026000021");
        s21.addScore("语文", 84.0);
        s21.addScore("数学", 89.0);
        s21.addScore("英语", 86.5);
        studentList.add(s21);

        Student s22 = new Student("孙二四", "2026000022");
        s22.addScore("生物", 87.0);
        s22.addScore("地理", 82.5);
        s22.addScore("政治", 89.5);
        studentList.add(s22);

        Student s23 = new Student("李二五", "2026000023");
        s23.addScore("数学", 92.0);
        s23.addScore("物理", 80.5);
        s23.addScore("化学", 88.5);
        s23.addScore("英语", 85.5);
        studentList.add(s23);

        Student s24 = new Student("王二六", "2026000024");
        s24.addScore("语文", 78.5);
        s24.addScore("历史", 85.0);
        s24.addScore("地理", 87.5);
        studentList.add(s24);

        Student s25 = new Student("刘二七", "2026000025");
        s25.addScore("英语", 89.5);
        s25.addScore("生物", 91.0);
        s25.addScore("政治", 83.0);
        studentList.add(s25);

        Student s26 = new Student("陈二八", "2026000026");
        s26.addScore("数学", 81.5);
        s26.addScore("物理", 86.0);
        s26.addScore("历史", 90.5);
        studentList.add(s26);

        Student s27 = new Student("杨二九", "2026000027");
        s27.addScore("语文", 88.0);
        s27.addScore("化学", 84.5);
        s27.addScore("地理", 80.0);
        s27.addScore("英语", 90.0);
        studentList.add(s27);

        Student s28 = new Student("赵三十", "2026000028");
        s28.addScore("数学", 85.5);
        s28.addScore("生物", 82.0);
        s28.addScore("政治", 87.0);
        studentList.add(s28);

        Student s29 = new Student("黄三一", "2026000029");
        s29.addScore("物理", 88.5);
        s29.addScore("化学", 91.5);
        s29.addScore("英语", 84.0);
        studentList.add(s29);

        Student s30 = new Student("周三二", "2026000030");
        s30.addScore("语文", 90.5);
        s30.addScore("历史", 86.0);
        s30.addScore("数学", 87.5);
        s30.addScore("生物", 89.0);
        studentList.add(s30);

        return studentList;
    }

    // 主方法：测试打印30条固定学生数据
    public static void main(String[] args) {
        // 获取固定的30条学生数据
        List<Student> fixedStudents = getFixed30Students();

        // 打印所有数据（验证是固定的死数据）
        System.out.println("===== 固定的30条学生死数据 =====");
        for (int i = 0; i < fixedStudents.size(); i++) {
            System.out.println((i + 1) + ". " + fixedStudents.get(i));
        }

        // 示例：获取第5条学生的详细成绩
        System.out.println("\n===== 第5条学生的详细成绩 =====");
        Student s5 = fixedStudents.get(4); // 索引从0开始，第5条是索引4
        System.out.println("姓名：" + s5.getName());
        System.out.println("学号：" + s5.getStudentId());
        System.out.println("各科成绩：");
        for (Map.Entry<String, Double> entry : s5.getScores().entrySet()) {
            System.out.println("  " + entry.getKey() + "：" + entry.getValue());
        }
    }
}
