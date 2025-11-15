package com.job;

public class ImportFileJob1 implements Runnable {
    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] 开始执行文件导入任务");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] 文件导入任务执行完毕");
    }
}