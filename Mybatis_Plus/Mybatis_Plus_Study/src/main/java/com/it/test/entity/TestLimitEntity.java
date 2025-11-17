package com.it.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "t_test_limit")
@Data
public class TestLimitEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    // 建议：保持字段名与数据库列名映射一致
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // 修正：建议使用 INSERT_UPDATE，这样插入和更新都会自动填充
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}