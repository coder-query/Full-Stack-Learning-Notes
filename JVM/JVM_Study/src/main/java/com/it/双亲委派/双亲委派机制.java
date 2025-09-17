package com.it.双亲委派;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/21 星期五 10:10
 */
public class 双亲委派机制 {
    public static void main(String[] args) {
        Object obj = new Object();
        String s = new String();
        Car demo = new Car();
        System.out.println(obj.getClass().getClassLoader()); /// 启动类加载器  c++
        System.out.println(s.getClass().getClassLoader());  /// 启动类加载器  c++
        System.out.println(demo.getClass().getClassLoader().getParent().getParent()); /// 启动类加载器  c++
        System.out.println(demo.getClass().getClassLoader().getParent());   /// 扩展类加载器
        System.out.println(demo.getClass().getClassLoader());   /// 应用类加载器

        System.out.println("BootstrapClassLoader加载的文件: ");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
        System.out.println("ExtClassLoader加载的文件: ");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("AppClassLoader加载的文件: ");
        System.out.println(System.getProperty("java.class.path"));
    }
}

class Car {
    public void run() {
        System.out.println("car is running");
    }
}
