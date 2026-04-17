package com.shuai.booteasyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.shuai.booteasyexcel.model.PurchaseModel;
import com.shuai.booteasyexcel.service.api.ExcelReadService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        try {
            // 设置响应头
            setExcelResponseHeaders(response, "采购模版");
            // 写入空数据的excel，只有表头
            EasyExcel.write(response.getOutputStream(), PurchaseModel.class)
                    .sheet("采购模版")
                    .doWrite(Collections.emptyList());
        } catch (IOException e) {
            throw new RuntimeException("下载模版失败", e);
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response) {
        try {
            // 设置响应头
            setExcelResponseHeaders(response, "采购数据");
            // 模拟查询数据库获取数据
            List<PurchaseModel> dataList = getPurchaseDataList();
            // 写入带数据的excel
            EasyExcel.write(response.getOutputStream(), PurchaseModel.class)
                    .sheet("采购数据")
                    .doWrite(dataList);
        } catch (IOException e) {
            throw new RuntimeException("下载excel失败", e);
        }
    }

    /**
     * 设置excel下载的响应头
     */
    private void setExcelResponseHeaders(HttpServletResponse response, String fileName) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = null;
        encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ".xlsx");
    }

    /**
     * 模拟查询数据库获取采购数据
     */
    private List<PurchaseModel> getPurchaseDataList() {
        List<PurchaseModel> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            PurchaseModel model = new PurchaseModel();
            model.setProductName("商品" + i);
            model.setSkuCode("SKU" + String.format("%04d", i));
            model.setSpecification("规格" + i);
            model.setRetailPrice(100.0 + i);
            model.setPurchasePrice(80.0 + i);
            model.setPurchaseQuantity(i * 10);
            model.setPurchaseDate(new Date());
            list.add(model);
        }
        return list;
    }
}
