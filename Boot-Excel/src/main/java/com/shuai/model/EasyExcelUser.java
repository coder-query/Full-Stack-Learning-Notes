package com.shuai.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class EasyExcelUser {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;
    @ExcelProperty(value = "年龄", index = 1)
    private Integer age;
}