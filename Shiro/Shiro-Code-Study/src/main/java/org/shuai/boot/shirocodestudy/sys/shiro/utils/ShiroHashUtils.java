package org.shuai.boot.shirocodestudy.sys.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class ShiroHashUtils {

    public static final String SALT = "123456@~realm~salt";

    public static final int HASH_ITERATIONS = 10;

    public static String encryptWithMd5(String password) {
        return new Md5Hash(password, SALT, HASH_ITERATIONS).toHex();
    }
    public static Boolean verifyWithMd5(String password, String encryptPassword) {
        return ShiroHashUtils.encryptWithMd5(password).equals(encryptPassword);
    }
    public static String encryptWithSha1(String password) {
        return new Sha1Hash(password, SALT, HASH_ITERATIONS).toHex();
    }
    public static Boolean verifyWithSha1(String password, String encryptPassword) {
        return  ShiroHashUtils.encryptWithSha1(password).equals(encryptPassword);
    }
    public static String encryptWithSha256(String password) {
        return new Sha256Hash(password, SALT, HASH_ITERATIONS).toHex();
    }
    public static Boolean verifyWithSha256(String password, String encryptPassword) {
        return ShiroHashUtils.encryptWithSha256(password).equals(encryptPassword);
    }
}
