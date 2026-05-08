package org.shaui.encrypt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "encrypt")
public class EncryptProperties {

    /** 是否启用加密功能 */
    private boolean enabled = true;

    /** 签名加密（MD2/MD5/SHA）的盐值 */
    private String signSalt = "!@#$%^&*()1234567890";

    /** AES CBC 模式加密密码 */
    private String aesPwdCbc = "97531(&%#!~";

    /** AES GCM 模式加密密码 */
    private String aesPwdGcm = "08642)*^$@~";
}