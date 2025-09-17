package org.example.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/24 星期一 14:06
 */
@ConfigurationProperties(prefix = "zsh.service")
public class settings {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
