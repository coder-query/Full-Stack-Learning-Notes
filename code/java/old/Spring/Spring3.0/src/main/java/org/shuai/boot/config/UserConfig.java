package org.shuai.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // 等同于子xml
@ComponentScan(basePackages = "org.shuai.boot.user")
public class UserConfig {
}
