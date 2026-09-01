package com.it;

class Test_Object {

    private byte[] bigSize = new byte[2 * 1024 * 1024];
    public Object instance= null;

}
public class  循环引用问题{

    public static void main (String [] args){

        Test_Object o1 = new Test_Object();
        System.out.println("o1地址:"+o1);
        Test_Object o2 = new Test_Object();
        System.out.println("o2地址:"+o2);

        o1.instance = o2;
        o2.instance = o1;
        System.out.println(o1.instance == o2.instance);

        o1 = null;
        o2 = null;

        System.gc();
    }
}