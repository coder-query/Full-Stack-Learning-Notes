package com.it.pojo.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 13:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private Integer productNum;
    private BigDecimal productPrice;
}
