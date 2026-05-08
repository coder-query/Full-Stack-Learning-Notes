package org.shaui.encrypt.constants;

public interface AesEncryptionConstant {
    /**
     * AES CBC 模式加密盐值。
     * <p><b>注意：</b>必须是十六进制字符串（0-9, A-F），且长度为偶数。
     */
     String aesSaltCbc = "0123456789ABCDEF0123456789ABCDEF";

    /**
     * AES GCM 模式加密盐值。
     * <p><b>注意：</b>必须是十六进制字符串（0-9, A-F），且长度为偶数。
     */
    String aesSaltGcm = "FEDCBA9876543210FEDCBA9876543210";
}
