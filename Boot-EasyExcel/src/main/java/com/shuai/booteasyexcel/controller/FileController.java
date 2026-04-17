package com.shuai.booteasyexcel.controller;

import com.shuai.booteasyexcel.service.api.ExcelReadService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 下载excel模版（只有表头，无数据）
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        excelReadService.downloadTemplate(response);
    }

    /**
     * 下载excel（带数据）
     */
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) {
        excelReadService.downloadExcel(response);
    }
}
