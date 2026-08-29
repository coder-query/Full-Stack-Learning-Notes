package org.shuai.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_stock")
@Data
public class Stock {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("product_name")
    private String productName;

    @TableField("product_count")
    private Integer productCount;


}
