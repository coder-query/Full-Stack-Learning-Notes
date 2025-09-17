package com.mayikt.method;

import com.mayikt.service.MessageInterface2;

/**
 * @ClassName Test015
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test015 {

    public static void main(String[] args) {
        Test015 test015 = new Test015();
//        MessageInterface2 messageInterface = () -> test015.objGet();
//        System.out.println(messageInterface.getMessage());
        MessageInterface2 messageInterface3= test015::objGet;
        System.out.println(messageInterface3.getMessage());
    }

    public String objGet() {
        return "每特教育666";
    }
}
