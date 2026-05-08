package org.shaui.encrypt.bean;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.shaui.encrypt.exception.EncryptionException;
import org.shaui.encrypt.exception.EncryptionExceptionEnums;
import org.shaui.encrypt.properties.EncryptProperties;

@Slf4j
public class RsaEncryptTemplate {
    private final EncryptProperties properties;

    public RsaEncryptTemplate(EncryptProperties properties) {
        this.properties = properties;
    }

    /**
     * RSA 公钥加密
     */
    public String encryptWithRsa(String originalContent) {
        if (StrUtil.isBlank(originalContent)) {
            log.error("传入的加密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        if (StrUtil.isBlank(properties.getRsaPublicKey())) {
            log.error("RSA公钥未配置，请在配置文件中设置 encrypt.rsa-public-key！！！");
            throw new EncryptionException(EncryptionExceptionEnums.RSA_KEY_BLANK);
        }
        RSA rsa = new RSA(null, properties.getRsaPublicKey());
        return rsa.encryptBase64(originalContent, KeyType.PublicKey);
    }

    /**
     * RSA 私钥解密
     */
    public String decryptWithRsa(String encryptedContent) {
        if (StrUtil.isBlank(encryptedContent)) {
            log.error("传入的解密内容不能为空，请检查！！！");
            throw new EncryptionException(EncryptionExceptionEnums.CONTENT_BLANK);
        }
        if (StrUtil.isBlank(properties.getRsaPrivateKey())) {
            log.error("RSA私钥未配置，请在配置文件中设置 encrypt.rsa-private-key！！！");
            throw new EncryptionException(EncryptionExceptionEnums.RSA_KEY_BLANK);
        }
        RSA rsa = new RSA(properties.getRsaPrivateKey(), null);
        return rsa.decryptStr(encryptedContent, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
        // 先生成密钥对
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        System.out.println("公钥: " + publicKey);
        System.out.println("私钥: " + privateKey);

        // 使用配置的方式
        EncryptProperties props = new EncryptProperties();
        props.setRsaPublicKey(publicKey);
        props.setRsaPrivateKey(privateKey);

        RsaEncryptTemplate template = new RsaEncryptTemplate(props);
        String encryptWithRsa = template.encryptWithRsa("123456");
        System.out.println("加密结果: " + encryptWithRsa);
        String decryptWithRsa = template.decryptWithRsa(encryptWithRsa);
        System.out.println("解密结果: " + decryptWithRsa);
    }
}
