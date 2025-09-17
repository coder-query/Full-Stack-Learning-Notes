package com.it.pojo.order;

import com.it.pojo.stock.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private BigDecimal totalAmount;
    private Integer userId;
    private String address;
    private List<Product> productList;
}
