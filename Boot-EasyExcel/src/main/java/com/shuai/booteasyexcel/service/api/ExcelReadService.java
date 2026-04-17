package com.shuai.booteasyexcel.service.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 包名称： com.shuai.booteasyexcel.service
 * 类名称：ExcelReadService
 * 类描述：@description TODO
 * 创建人：@author shuaihong-coding
 * 创建时间：2026-01-05 00:02
 */

public interface ExcelReadService {

    void simpleReadExcel(MultipartFile file);

    /**
     * 下载excel模版（只有表头，无数据）
     */
    void downloadTemplate(HttpServletResponse response);

    /**
     * 下载excel（带数据）
     */
    void downloadExcel(HttpServletResponse response);
}
