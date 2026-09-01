package com.shuai.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/5/28 0028
 */

@RestController
public class Log4j2Controller {

    private static final Logger logger = LogManager.getLogger(Log4j2Controller.class);

    @GetMapping("/log")
    public String testLog4j2() {
        logger.debug("帅宏-coding--- Debug 级别 message");
        logger.info("帅宏-coding--- Info级别 message");
        logger.warn("帅宏-coding--- Warn级别 message");
        logger.error("帅宏-coding--- Error 级别 message");
        return "成功~";
    }
}
