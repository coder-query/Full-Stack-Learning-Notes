package org.example.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt")
public class EncryptProperties {

    private boolean enabled = true;
    private String algorithm = "SHA-256";
    private int saltLength = 8;
    private boolean uppercase = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getSaltLength() {
        return saltLength;
    }

    public void setSaltLength(int saltLength) {
        this.saltLength = saltLength;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }
}