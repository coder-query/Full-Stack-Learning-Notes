package com.shuai.it.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/5/28 0028
 */
@RestController
@Slf4j
public class LogBackController {

    @GetMapping("/test/log")
    public String testLogBack() {
        log.trace("trace信息");
        log.debug("debug信息");
        log.info("info信息~");
        log.warn("warn信息~");
        log.error("error信息~");
        return "日志记录已生成~";
    }
}
