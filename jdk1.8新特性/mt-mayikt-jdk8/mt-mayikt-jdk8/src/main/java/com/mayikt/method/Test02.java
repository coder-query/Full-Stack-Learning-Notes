package com.mayikt.method;

import com.mayikt.entity.MessageEntity;
import com.mayikt.entity.UserEntity;
import com.mayikt.service.MessageInterface2;

/**
 * @ClassName Test02
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test02 {
    public static void main(String[] args) {
//        MessageInterface2 messageInterface2 = () -> {
//            return new MessageEntity();
//        };
        /**
         *  构造函数引入 遵循规范：函数接口返回类型::new
         */
        MessageInterface2 messageInterface3 = String::new;
        System.out.println(messageInterface3.getMessage());
    }
}
