package com.anxiang.mybatisspring.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 18:38
 */
@Data
public class Goods {
    private Long id;
    private String type;
    private String name;
    private BigDecimal price;
    private Integer num;
    private LocalDateTime addTime;
}
