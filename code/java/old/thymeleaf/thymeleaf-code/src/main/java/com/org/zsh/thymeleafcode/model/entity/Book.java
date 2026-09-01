package com.org.zsh.thymeleafcode.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图书表
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 图书ID
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    /**
     * 图书名称
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 图书作者
     */
    @TableField(value = "author_name")
    private String authorName;

    /**
     * 图书价格
     */
    @TableField(value = "price")
    private Double price;

    /**
     * 分类ID,关联分类表主键
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 是否上架 1:上架 0:下架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 图书主图地址
     */
    @TableField(value = "book_url")
    private String bookUrl;

    /**
     * 真实地址
     */
    @TableField(value = "book_address")
    private String bookAddress;

    @TableField(exist = false)
    private String categoryName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}