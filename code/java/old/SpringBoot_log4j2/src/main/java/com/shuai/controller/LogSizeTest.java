package com.shuai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogSizeTest {
    private static final Logger logger = LoggerFactory.getLogger(LogSizeTest.class);

    @GetMapping("test/logSize")
    public String testLogSize() {
        // 生成一个约1KB的测试消息
        String testMessage = String.format("%01024d", 0);

        // 循环写入日志，每次写入1KB，目标是生成超过20MB的数据
        for (int i = 0; i < 21 * 1024; i++) { // 21 * 1024KB = ~21MB
            if (i % 1000 == 0) {
                System.out.println("已写入: " + i / 1024 + "MB");
            }
            logger.info("Test log message: {}", testMessage);
            logger.warn("Test log message: {}", testMessage);
            logger.error("Test log message: {}", testMessage);
        }
        System.out.println("日志生成完成，请检查logs目录");
        return "日志生成完成，请检查logs目录";
    }
}