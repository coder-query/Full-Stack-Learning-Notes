package org.shuai.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class EasyPoiUser {
    @Excel(name = "姓名",width = 20)
    private String name;
    @Excel(name = "年龄",width = 15)
    private Integer age;
}