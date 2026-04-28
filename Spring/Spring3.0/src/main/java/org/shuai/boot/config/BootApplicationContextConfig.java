package org.shuai.boot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UserConfig.class, OrderConfig.class})
public class BootApplicationContextConfig {
}
