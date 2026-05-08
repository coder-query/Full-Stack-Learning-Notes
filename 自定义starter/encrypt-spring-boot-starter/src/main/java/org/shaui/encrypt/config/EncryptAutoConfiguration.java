package org.shaui.encrypt.config;

import org.shaui.encrypt.bean.SignEncryptTemplate;
import org.shaui.encrypt.properties.EncryptProperties;
import org.shaui.encrypt.bean.AesEncryptTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = "encrypt",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptAutoConfiguration {

    @Bean
    public AesEncryptTemplate aesEncryptTemplate(EncryptProperties properties) {
        return new AesEncryptTemplate(properties);
    }

    @Bean
    public SignEncryptTemplate signEncryptTemplate(EncryptProperties properties) {
        return new SignEncryptTemplate(properties);
    }

}