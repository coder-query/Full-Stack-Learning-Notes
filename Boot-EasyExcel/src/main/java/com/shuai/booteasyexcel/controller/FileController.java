package com.shuai.booteasyexcel.controller;

import com.shuai.booteasyexcel.service.api.ExcelReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 包名称： com.shuai.booteasyexcel.controller
 * 类名称：FileController
 * 类描述：@description TODO
 * 创建人：@author shuaihong-coding
 * 创建时间：2026-01-05 00:13
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final ExcelReadService excelReadService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        excelReadService.simpleReadExcel(file);
        return "ok";
    }
}
