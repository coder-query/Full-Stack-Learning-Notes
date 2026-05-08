package org.shaui.encrypt.bean;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.shaui.encrypt.constants.AesEncryptionConstant;
import org.shaui.encrypt.exception.EncryptionException;
import org.shaui.encrypt.exception.EncryptionExceptionEnums;
import org.shaui.encrypt.properties.EncryptProperties;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class AesEncryptTemplate {

    private final EncryptProperties properties;

    public AesEncryptTemplate(EncryptProperties properties) {
        this.properties = properties;
    }

    public String encryptWithAES_CBC(String originalContent){
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        String aesPwdCBC = properties.getAesPwdCbc();
        BytesEncryptor standardBytesEncryptor = Encryptors.standard(aesPwdCBC, AesEncryptionConstant.aesSaltCbc);
        byte[] encrypt = standardBytesEncryptor.encrypt(originalContent.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public String decryptWithAES_CBC(String encryptedContent) {
        if (StrUtil.isBlank(encryptedContent)) {
            log.error("传入的解密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        try {
            String aesPwdCBC = properties.getAesPwdCbc();
            BytesEncryptor standardBytesEncryptor = Encryptors.standard(aesPwdCBC, AesEncryptionConstant.aesSaltCbc);
            // 1. Base64 解码得到密文字节
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedContent);
            // 2. 解密
            byte[] decryptedBytes = standardBytesEncryptor.decrypt(encryptedBytes);
            // 3. 转回字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                log.error("解密内容失败！！！，请检查密钥是否正确,异常信息 --》", e);
                throw new EncryptionException(EncryptionExceptionEnums.CONTENT_DECRYPT_ERROR);
            }
            log.error("解密内容失败！！！异常信息 --》", e);
            throw e;
        }
    }

    public String decryptWithAES_GCM(String encryptedContent) {
        if (StrUtil.isBlank(encryptedContent)) {
            log.error("传入的解密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        try {
            String aesPwdGCM = properties.getAesPwdGcm();
            BytesEncryptor strongerBytesEncryptor = Encryptors.stronger(aesPwdGCM, AesEncryptionConstant.aesSaltGcm);
            // 1. Base64 解码得到密文字节
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedContent);
            // 2. 解密
            byte[] decryptedBytes = strongerBytesEncryptor.decrypt(encryptedBytes);
            // 3. 转回字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                log.error("解密内容失败！！！，请检查密钥是否正确,异常信息 --》", e);
                throw new EncryptionException(EncryptionExceptionEnums.CONTENT_DECRYPT_ERROR);
            }
            log.error("解密内容失败！！！异常信息 --》", e);
            throw e;
        }
    }

    public String encryptWithAES_GCM(String originalContent){
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        String aesPwdGCM = properties.getAesPwdGcm();
        BytesEncryptor strongerBytesEncryptor = Encryptors.stronger(aesPwdGCM,AesEncryptionConstant.aesSaltGcm);
        byte[] encrypt = strongerBytesEncryptor.encrypt(originalContent.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static void main(String[] args) {
        EncryptProperties encryptProperties1 = new EncryptProperties();
        encryptProperties1.setAesPwdGcm("123456");
        AesEncryptTemplate aesEncryptTemplate1 = new AesEncryptTemplate(encryptProperties1);
        String s1 = aesEncryptTemplate1.encryptWithAES_GCM("123");
        System.out.println(s1);
        EncryptProperties encryptProperties2 = new EncryptProperties();
        encryptProperties2.setAesPwdGcm("12345622");
        AesEncryptTemplate aesEncryptTemplate2 = new AesEncryptTemplate(encryptProperties2);
        String s2 = aesEncryptTemplate2.decryptWithAES_GCM(s1);
        System.out.println(s2);
    }
}