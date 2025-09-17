package org.example.tools.Impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.tools.EncryptTools;

import javax.tools.Tool;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/24 星期一 14:05
 */
public class Md5Tool implements EncryptTools {
	@Override
	public String encrypt(String plainText) {
		return DigestUtils.md5Hex(plainText);
	}
}
