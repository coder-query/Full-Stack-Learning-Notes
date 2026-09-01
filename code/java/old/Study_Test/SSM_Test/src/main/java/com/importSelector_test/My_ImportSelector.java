package com.importSelector_test;

import com.it.config.ItBeansConfig;
import com.other.config.OtherBeansConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 23:45
 */
public class My_ImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{ItBeansConfig.class.getName(), OtherBeansConfig.class.getName()};
	}
}
