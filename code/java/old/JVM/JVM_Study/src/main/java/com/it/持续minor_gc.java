package com.it;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shuaihong-coding
 * @date 2026-02-15 21:00
 */
public class 持续minor_gc {
    public static void main(String[] args) throws InterruptedException {
        List<String> strings = new ArrayList<>();
        while (true) {
            String timeStr = Instant.now().toString();
            strings.add(timeStr);
            System.out.println("Current time: " + timeStr);
            Thread.sleep(10);
        }
    }
}
