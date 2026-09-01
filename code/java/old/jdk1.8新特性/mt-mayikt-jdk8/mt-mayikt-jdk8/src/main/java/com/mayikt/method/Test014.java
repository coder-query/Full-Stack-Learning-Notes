package com.mayikt.method;

import com.mayikt.entity.UserEntity;
import com.mayikt.service.UserInterface;

/**
 * @ClassName Test014
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test014 {
    public static void main(String[] args) {
//        UserInterface userInterface = () -> new UserEntity();
        // 构造函数引入  函数接口返回类型 UserEntity::new.----默认的情况下执行无参构造函数
        UserInterface userInterface3 = UserEntity::new;
        System.out.println(userInterface3.getUser());
    }
}
