package org.shaui.encrypt.bean;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.Digester;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.shaui.encrypt.exception.EncryptionException;
import org.shaui.encrypt.exception.EncryptionExceptionEnums;
import org.shaui.encrypt.properties.EncryptProperties;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

import java.nio.charset.StandardCharsets;

@Slf4j
public class SignEncryptTemplate {

    private final EncryptProperties properties;

    public SignEncryptTemplate(EncryptProperties properties) {
        this.properties = properties;
    }

    public String encryptWithMD2(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return DigestUtils.md2Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithMD2(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithMD2(originalContent), encryptContent);
    }

    public String encryptWithMD5(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return DigestUtils.md5Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithMD5(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithMD5(originalContent), encryptContent);
    }

    public String encryptWithSha1(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return DigestUtils.sha1Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithSha1(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithSha1(originalContent), encryptContent);
    }

    public String encryptWithSha256(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
       return DigestUtils.sha256Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithSha256(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithSha256(originalContent), encryptContent);
    }

    public String encryptWithSha384(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return DigestUtils.sha384Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithSha384(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithSha384(originalContent), encryptContent);
    }

    public String encryptWithSha512(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return DigestUtils.sha512Hex((originalContent + properties.getSignSalt()).getBytes());
    }

    public Boolean verifyEncryptWithSha512(String originalContent, String encryptContent) {
        if (StrUtil.isBlank(originalContent) || StrUtil.isBlank(encryptContent)) {
            log.error("传入的内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        return StrUtil.equals(encryptWithSha512(originalContent), encryptContent);
    }
}