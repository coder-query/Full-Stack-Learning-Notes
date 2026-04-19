package com.shuai.booteasyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseModel {

    @ExcelProperty(value = "后台商品名称")
    private String productName;           // 后台商品名称
    @ExcelProperty(value = "后台商品SKU编码")
    private String skuCode;               // 后台商品SKU编码
    @ExcelProperty(value = "后台规格")
    private String specification;         // 后台规格
    @ExcelProperty(value = "后台零售价")
    private Double retailPrice;           // 后台零售价
    @ExcelProperty(value = "后台采购价")
    private Double purchasePrice;         // 后台采购价
    @ExcelProperty(value = "采购数量")
    private Integer purchaseQuantity;     // 采购数量
    @DateTimeFormat(value = "yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "采购日期")
    private Date purchaseDate;
}