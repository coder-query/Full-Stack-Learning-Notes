package com.shuai;

import com.shuai.model.TestModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(CrowdFrequencyEnum.values()));

        TestModel testModel1 = new TestModel(1, "张三", 20, "男");
        TestModel testModel2 = new TestModel(2, "李四", 21, "女");
        TestModel testModel3 = new TestModel(3, "王五", 22, "男");
        TestModel testModel4 = new TestModel(4, "赵六", 23, "女");
        TestModel testModel5 = new TestModel(5, "钱七", 24, "男");
        TestModel testModel6 = new TestModel(6, "孙八", 25, "女");
        TestModel testModel7 = new TestModel(7, "周九", 26, "男");
        TestModel testModel8 = new TestModel(8, "吴十", 27, "女");
        TestModel testModel9 = new TestModel(9, "郑十一", 28, "男");
        TestModel testModel10 = new TestModel(10, "黄十二", 29, "女");
        TestModel testModel11 = new TestModel(13, "黄十三", 30, "男");
        TestModel testModel12 = new TestModel(13, "黄十四", 31, "男");
        TestModel testModel13 = new TestModel(13, "黄十五", 32, "男");
        TestModel testModel14 = new TestModel(null, "黄十六", 33, "男");
        ArrayList<TestModel> testModels = new ArrayList<>(Arrays.asList(testModel1, testModel2, testModel3, testModel4, testModel5, testModel6, testModel7, testModel8, testModel9, testModel10, testModel11));
        testModels.add(testModel12);
        testModels.add(testModel13);
        testModels.add(testModel14);
//        testModels.stream().sorted(Comparator.comparing(TestModel::getAge, Comparator.reverseOrder())).forEach(System.out::println);
        testModels.stream()
                .sorted(Comparator.comparing(TestModel::getAge,Comparator.nullsFirst(Comparator.reverseOrder())))
                .forEach(System.out::println);
    }
}
