package org.example.conf;

import org.apache.commons.codec.digest.DigestUtils;

public enum EncryptionAlgorithm {

    MD5("MD5") {
        @Override
        public String encrypt(String input) {
            return DigestUtils.md5Hex(input);
        }
    },

    SHA_1("SHA-1", "SHA1") {
        @Override
        public String encrypt(String input) {
            return DigestUtils.sha1Hex(input);
        }
    },
    SHA_256("SHA-256", "SHA256") {
        @Override
        public String encrypt(String input) {
            return DigestUtils.sha256Hex(input);
        }
    },

    SHA_512("SHA-512", "SHA512") {
        @Override
        public String encrypt(String input) {
            return DigestUtils.sha512Hex(input);
        }
    };

    private final String[] names;

    EncryptionAlgorithm(String... names) {
        this.names = names;
    }

    public abstract String encrypt(String input);

    public static EncryptionAlgorithm fromString(String algorithm) {
        if (algorithm == null) {
            return SHA_256;
        }
        String upper = algorithm.toUpperCase();
        for (EncryptionAlgorithm algo : values()) {
            for (String name : algo.names) {
                if (name.equals(upper)) {
                    return algo;
                }
            }
        }
        return SHA_256;
    }
}