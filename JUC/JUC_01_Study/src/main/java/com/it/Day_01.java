package com.it;

import java.util.Arrays;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/2/17 星期一 0:09
 */
public class Day_01 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int nums[] = new int[]{2, 4, 7, 5, 1, 9};
        // 1. 给数组排序,最原始的就是api排序
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
        // 2. 给数组排序,也可以利用多线程去休眠
        for (int num : nums) {
            new Thread(()->{
                try {
                    Thread.sleep(num * 1000);
                    System.out.print(num+" ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
