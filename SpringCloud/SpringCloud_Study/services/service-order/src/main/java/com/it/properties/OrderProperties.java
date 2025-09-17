package com.it.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 19:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "order")
public class OrderProperties {
    private Long timeout;
    private Integer maxSize;
    private String dataUrl;
}
