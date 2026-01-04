package com.shuai.booteasyexcel.service.api;

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
}
