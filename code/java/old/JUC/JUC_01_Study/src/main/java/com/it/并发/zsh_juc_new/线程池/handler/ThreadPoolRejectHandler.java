package com.it.并发.zsh_juc_new.线程池.handler;

import com.it.并发.zsh_juc_new.线程池.pool.ThreadPoolUtils;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtils.retryExecutor();
        try {
            threadPoolExecutor.execute(r);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
