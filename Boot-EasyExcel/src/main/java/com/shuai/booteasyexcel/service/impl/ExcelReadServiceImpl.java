package com.shuai.booteasyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.shuai.booteasyexcel.model.PurchaseModel;
import com.shuai.booteasyexcel.service.api.ExcelReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 包名称： com.shuai.booteasyexcel.service.impl
 * 类名称：ExcelReadServiceImpl
 * 类描述：@description TODO
 * 创建人：@author shuaihong-coding
 * 创建时间：2026-01-05 00:03
 */
@Service
@Slf4j
public class ExcelReadServiceImpl implements ExcelReadService {
    @Override
    public void simpleReadExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, PurchaseModel.class, new PageReadListener<PurchaseModel>(dataList -> {
                dataList.forEach(data -> {
                    log.info("读取到excel中数据一条: {}", data);
                });
            })).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
