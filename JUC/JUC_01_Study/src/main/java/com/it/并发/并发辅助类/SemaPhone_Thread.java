package com.it.并发.并发辅助类;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/18 星期二 19:26
 */

/**
 * 案例表述: 假设停车场有3个空位,并且没有停车,先后到来5辆车
 * 保安大叔只能允许3辆车停入车位
 * 有了空余车位,后面来的车才能进入停车场
 */

/**
 * 1. 定义停车场保安对象Semaphore,管理3个车位
 *       Semaphore semaphore = new Semaphore(3);
 * 2. 车辆入场 : 申请许可,抢占一个停车位
 *       semaphore.acquire();
 * 3. 车辆出场 : 释放许可,开走，释放一个停车位
 *       semaphore.release();
 *
 */
public class SemaPhone_Thread {
    public static void main(String[] args) {
            // 初始化信号量，3个车位
            Semaphore semaphore = new Semaphore(3);

            // 6个线程，模拟6辆车
            for (int i = 0; i < 5; i++) {
                new Thread(()->{
                    try {
                        // 抢占一个停车位
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " 抢到了一个停车位！！");
                        // 停一会儿车
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                        System.out.println(Thread.currentThread().getName() + " 离开停车位！！");
                        // 开走，释放一个停车位
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, String.valueOf(i)).start();
            }
    }
}
