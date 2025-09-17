package com.importSelector_test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 23:55
 */
@Configuration
@Import(My_ImportSelector.class)
public class zshConfig {
}
