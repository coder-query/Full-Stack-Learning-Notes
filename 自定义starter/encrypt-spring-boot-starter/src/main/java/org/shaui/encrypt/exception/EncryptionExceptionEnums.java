package org.shaui.encrypt.exception;

public enum EncryptionExceptionEnums {

    CONTENT_BLANK("传入的加密内容为空！！！"),

    CONTENT_DECRYPT_ERROR("解密内容失败！！！，请检查密钥是否正确"),

    RSA_KEY_BLANK("RSA密钥未配置！！！"),

    ;

    private String msg;

    EncryptionExceptionEnums(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }



}
