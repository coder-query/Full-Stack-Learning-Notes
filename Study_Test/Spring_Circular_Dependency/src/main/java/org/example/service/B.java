package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

    @Autowired
    private A a;

    public B() {
    }

    public B(A a) {
        this.a = a;
    }

    /**
     * 获取
     *
     * @return a
     */
    public A getA() {
        return a;
    }

    /**
     * 设置
     *
     * @param a
     */
    public void setA(A a) {
        this.a = a;
    }

}
