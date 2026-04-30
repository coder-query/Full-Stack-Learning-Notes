package org.example.conf;

import cn.hutool.core.lang.UUID;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {

    private final EncryptProperties properties;

    public EncryptUtils(EncryptProperties properties) {
        this.properties = properties;
    }

    public String md5(String input) {
        String result = DigestUtils.md5Hex(input);
        return properties.isUppercase() ? result.toUpperCase() : result;
    }

    public String sha1(String input) {
        String result = DigestUtils.sha1Hex(input);
        return properties.isUppercase() ? result.toUpperCase() : result;
    }

    public String sha256(String input) {
        String result = DigestUtils.sha256Hex(input);
        return properties.isUppercase() ? result.toUpperCase() : result;
    }

    public String sha512(String input) {
        String result = DigestUtils.sha512Hex(input);
        return properties.isUppercase() ? result.toUpperCase() : result;
    }

    public String encrypt(String input) {
        EncryptionAlgorithm algorithm = EncryptionAlgorithm.fromString(properties.getAlgorithm());
        String result = algorithm.encrypt(input);
        return properties.isUppercase() ? result.toUpperCase() : result;
    }

    public String generateSalt() {
        return UUID.fastUUID().toString(true).substring(0, properties.getSaltLength());
    }

    public String encryptWithSalt(String input) {
        String salt = generateSalt();
        return salt + ":" + encrypt(input + salt);
    }
}