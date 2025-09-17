package com.it.并发.Stream流;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 14:21
 */
public class Stream_Test {
    public static void main(String[] args) {

        /**
         * 数据准备
         */
        User user1 = new User(UUID.randomUUID().toString().substring(0,5), 18, 1);
        User user2 = new User(UUID.randomUUID().toString().substring(0,5), 19, 0);
        User user3 = new User(UUID.randomUUID().toString().substring(0,5), 20, 1);
        User user4 = new User(UUID.randomUUID().toString().substring(0,5), 21, 0);
        User user5 = new User(UUID.randomUUID().toString().substring(0,5), 22, 1);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        /**
         *  1.筛选出年龄为偶数的
         *  2.筛选出性别为男的
         */

        users.stream()
                .filter((user)->user.getAge()%2==0)
                .filter((user)->user.getSex()==1)
                .forEach((user)-> System.out.println(user));
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private String name;
    private int age;
    /// 1 : 男  / 0 : 女
    private int sex;
}
