package com.it.测试;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) throws IOException {
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("hello");
//            return "hello";
//        });
//        future.join();

//        Executor executor = Executors.newFixedThreadPool(10);
//        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
//            String uuid = UUID.randomUUID().toString();
//            System.out.println("任务A:" + uuid+"ThreadName:"+Thread.currentThread().getName());
//            return uuid;
//        });
//        CompletableFuture<String> taskB = taskA.thenApplyAsync(result -> {
//            String task = "任务B:"+result.toString();
//            System.out.println(task+"ThreadName:"+Thread.currentThread().getName());
//            return result;
//        },executor);
//        System.out.println("main线程:"+taskB.join()+"ThreadName:"+Thread.currentThread().getName());
//        System.in.read();

//        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
//            String uuid = UUID.randomUUID().toString();
//            System.out.println("任务A:" + uuid+"ThreadName:"+Thread.currentThread().getName());
//            return uuid;
//        });
//        taskA.thenAccept(result -> {
//            System.out.println("消费者拿到结果"+result);
//        });
//        System.in.read();

//        CompletableFuture.runAsync(() -> {
//            System.out.println("hello"+Thread.currentThread().getName());
//        }).thenRun(() -> {
//            System.out.println("hello2"+Thread.currentThread().getName());
//        });
//        System.in.read();

//        long start = System.currentTimeMillis();
//        CompletableFuture<Void> taskC = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务A");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            int i = 1/0;
//            return 23;
//        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务B");
//            return 12;
//        }),result->{
//            System.out.println("任务C");
//            System.out.println(result);
//        }).exceptionally(ex->{
//            System.out.println("异常"+ex.getMessage());
//            return null;
//        });
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//        System.in.read();

        long start = System.currentTimeMillis();
        CompletableFuture<Void> taskC = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = 1/0;
            return 23;
        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("任务B");
            return 12;
        }),result->{
            System.out.println("任务C");
            System.out.println(result);
        }).handle((r, ex)->{
            System.out.println(r);
            System.out.println("异常"+ex.getMessage());
            return r;
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.in.read();
    }
}
