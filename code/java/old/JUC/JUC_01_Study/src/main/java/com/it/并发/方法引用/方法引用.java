package com.it.并发.方法引用;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/20 星期四 20:27
 */
public class 方法引用 {
    public static void main(String[] args) {

        User u1 = new User("张三", 18);
        User u2 = new User("李四", 19);
        User u3 = new User("王五", 20);
        User[] users = new User[]{u1, u2, u3};
        /// 按照年龄倒序排序


        /// 匿名内部类
//        Arrays.sort(users, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o2.getAge() - o1.getAge();
//            }
//        });

//        /// Lambda表达式
//        Arrays.sort(users, (x, y) -> Compare.compare(x, y));
//
        /// 静态方法引用
        Arrays.sort(users, Compare::compare1);

        /// 输出打印
        for (User user : users) {
            System.out.print(" " + user);
        }
        System.out.println("\n-------------------------------------");
        /// 实例方法引用
        Compare compare = new Compare();
        Arrays.sort(users, compare::compare2);

        /// 输出打印
        for (User user : users) {
            System.out.print(" " + user);
        }
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
}

class Compare {
    public Compare() {
    }

    /// 静态方法
    public static int compare1(User u1, User u2) {
        return u2.getAge() - u1.getAge();
    }

    /// 非静态方法
    public int compare2(User u1, User u2) {
        return u2.getAge() - u1.getAge();
    }
}
