package com.it.JUC;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/7 星期五 0:08
 */
public class My_Reject_Handle implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("线程池已满，拒绝执行任务");
    }
}
