package com.importSelector_Test;

import com.it.config.ItBeansConfiguration;
import com.other.config.OtherBeansConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 10:30
 */
public class My_ImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{ItBeansConfiguration.class.getName(), OtherBeansConfiguration.class.getName()};
	}
}
