package org.example.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "encrypt", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptAutoConfiguration {

    @Bean
    public EncryptUtils encryptUtils(EncryptProperties properties) {
        return new EncryptUtils(properties);
    }

}