package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class B {

//    @Autowired
    private A a;

    public B() {
    }

    @Autowired
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
