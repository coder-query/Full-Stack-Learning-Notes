package com.importSelector_Test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 10:31
 */
@Configuration
@Import(My_ImportSelector.class)
public class My_ImportSelector_Configuration {
}
