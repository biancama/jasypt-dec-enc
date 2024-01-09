package com.ing.api.contacting;

import org.jasypt.encryption.StringEncryptor;

public class EncDec {
    final private String JASYPT_INITIAL_MARKER = "ENC(";
    final private String JASYPT_FINAL_MARKER = ")";

    private StringEncryptor stringEncryptor;
    EncDec(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public String decrypt(String encryptedPwd) {
        return encryptedPwd.startsWith(JASYPT_INITIAL_MARKER) ?  stringEncryptor.decrypt(encryptedPwd.substring(JASYPT_INITIAL_MARKER.length(), encryptedPwd.length() - JASYPT_FINAL_MARKER.length())) : encryptedPwd;
    }

    public String encrypt(String pwdInClear) {
        return String.format("%s%s%s", JASYPT_INITIAL_MARKER, stringEncryptor.encrypt(pwdInClear), JASYPT_FINAL_MARKER);
    }
}
