package com.it.并发.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/19 星期三 12:03
 */
public class ArrayBlockingQueue_Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // 第一组方法：add remove element
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));
//        // System.out.println(queue.add("d"));
//        // System.out.println(queue.element());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        //System.out.println(queue.remove());
//        //System.out.println(queue.element());
        // 第二组：offer poll peek
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("b"));
//        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d"));
//        System.out.println(queue.peek());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.peek());
        // 第三组：put take
//        queue.put("a");
//        queue.put("b");
//        queue.put("c");
//        System.out.println(queue.take());
//        queue.put("d");
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
        // 第四组：offer poll
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d", 5, TimeUnit.SECONDS));
    }
}

