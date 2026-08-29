package com.mayikt;

import com.mayikt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName Test07
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test07 {
    public static void main(String[] args) {
        ArrayList<UserEntity> userLists = new ArrayList<>();
        userLists.add(new UserEntity("xiaomin", 12));
        userLists.add(new UserEntity("xiaoxiao", 6));
        userLists.add(new UserEntity("xiaojun", 23));
        userLists.sort(new Comparator<UserEntity>() {
            @Override
            public int compare(UserEntity o1, UserEntity o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        userLists.sort((o1, o2) -> o1.getAge() - o2.getAge()
 );
        userLists.forEach((t) -> {
            System.out.println(t.toString());
        });
    }
}
