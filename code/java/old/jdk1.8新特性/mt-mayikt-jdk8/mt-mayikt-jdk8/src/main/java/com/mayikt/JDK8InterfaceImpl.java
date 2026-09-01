package com.mayikt;

/**
 * @ClassName JDK8InterfaceImpl
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class JDK8InterfaceImpl implements JDK8Interface {
    /**
     * 默认的情况下必须重写接口中抽象方法 默认和静态方法是不需要必须重写
     */
    @Override
    public void add() {
        System.out.println("add");
    }
}
