package org.shaui.encrypt.exception;


public class EncryptionException extends RuntimeException{

    public EncryptionException(EncryptionExceptionEnums encryptionExceptionEnums){
        super(encryptionExceptionEnums.getMsg());
    }

}
