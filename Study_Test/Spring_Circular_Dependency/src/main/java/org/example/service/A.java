package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class A {

//    @Autowired
    private B b;
    // 基本数据类型
    // 引用数据类型


    public A() {
    }

    @Autowired
    public A(B b) {
        this.b = b;
    }


    /**
     * 获取
     *
     * @return b
     */
    public B getB() {
        return b;
    }

    /**
     * 设置
     *
     * @param b
     */
    public void setB(B b) {
        this.b = b;
    }

    public String toString() {
        return "A{b = " + b + "}";
    }
}
