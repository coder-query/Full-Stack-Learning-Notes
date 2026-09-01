package com.it.测试;

import java.util.concurrent.TimeUnit;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/20 星期四 11:21
 */
public class test_02 {
    private static  int num = 0;
    public static synchronized Integer incr(){
        return num++; /// num = num + 1;
    }
    public static void main(String[] args) {
        for(int i = 0;i<10_0000;i++){
            new Thread(()->{  /// 10万条线程
                incr();
            }).start();
        }
//        new Thread(()->{
//            for(int i = 0;i<10_0000;i++){
//                incr();
//            }
//        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num);
    }
}
