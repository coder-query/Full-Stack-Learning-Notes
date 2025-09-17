package org.example.tools.Impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.tools.EncryptTools;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/24 星期一 14:06
 */
public class Sha256Tool implements EncryptTools {
	@Override
	public String encrypt(String plainText) {
		return DigestUtils.sha256Hex(plainText);
	}
}
