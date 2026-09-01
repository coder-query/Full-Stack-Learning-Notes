package com.it;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/21 星期五 20:22
 */
public class 查看堆空间大小 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024/1024+"GB"); /// 内存的 1 / 4
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"MB");  /// 当前内存使用量
        System.out.println("----------------------------");
    }
}
