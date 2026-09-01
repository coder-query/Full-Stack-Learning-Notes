package com.it;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/2 星期日 12:55
 */
public class 对象的内存布局 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());


        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
