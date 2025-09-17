package org.example.conf;

import org.example.tools.Impl.Md5Tool;
import org.example.tools.Impl.Sha256Tool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/24 星期一 14:07
 */
@Configuration
@EnableConfigurationProperties(settings.class)
public class EncryptionConfiguration {
	@Bean
	public Md5Tool md5Tool() {
		System.out.println("MD5加密API注入IOC...");
		return new Md5Tool();
	}

	@Bean
	@ConditionalOnProperty(prefix = "zsh.service", name = "type", havingValue = "SHA256")
	public Sha256Tool sha256Tool() {
		System.out.println("SHA256加密API注入IOC...");
		return new Sha256Tool();
	}
}
