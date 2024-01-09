package com.ing.api.contacting;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class JasyptEncryptorConfigurationProperties {

    /**
     * Master Password used for Encryption/Decryption of properties.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     */
    private String password;

    /**
     * Encryption/Decryption Algorithm to be used by Jasypt. For more info on how to get available algorithms visit:
     * <a href="http://www.jasypt.org/cli.html"/>Jasypt CLI Tools Page</a>. Default Value is {@code "PBEWITHHMACSHA512ANDAES_256"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     */
    private String algorithm = "PBEWITHHMACSHA512ANDAES_256";

    /**
     * Number of hashing iterations to obtain the signing key. Default Value is {@code "1000"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     */
    private String keyObtentionIterations = "1000";

    /**
     * The size of the pool of encryptors to be created. Default Value is {@code "1"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.StringPBEConfig#getPoolSize()
     */
    private String poolSize = "1";

    /**
     * The name of the {@link java.security.Provider} implementation to be used by the encryptor for obtaining the
     * encryption algorithm. Default Value is {@code null}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.StringPBEConfig#getProviderName()
     */
    private String providerName = null;

    /**
     * The class name of the {@link java.security.Provider} implementation to be used by the encryptor for obtaining the
     * encryption algorithm. Default Value is {@code null}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.SimpleStringPBEConfig#setProviderClassName(String)
     */
    private String providerClassName = null;

    /**
     * A {@link org.jasypt.salt.SaltGenerator} implementation to be used by the encryptor. Default Value is
     * {@code "org.jasypt.salt.RandomSaltGenerator"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.StringPBEConfig#getSaltGenerator()
     */
    private String saltGeneratorClassname = "org.jasypt.salt.RandomSaltGenerator";

    /**
     * A {@link org.jasypt.iv.IvGenerator} implementation to be used by the encryptor. Default Value is
     * {@code "org.jasypt.iv.RandomIvGenerator"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.StringPBEConfig#getIvGenerator()
     */
    private String ivGeneratorClassname = "org.jasypt.iv.RandomIvGenerator";

    /**
     * Specify the form in which String output will be encoded. {@code "base64"} or {@code "hexadecimal"}. Default Value
     * is {@code "base64"}.
     *
     * @see org.jasypt.encryption.pbe.PBEStringEncryptor
     * @see org.jasypt.encryption.pbe.config.StringPBEConfig#getStringOutputType()
     */
    private String stringOutputType = "base64";

    public static JasyptEncryptorConfigurationProperties createProperties() {
        //TODO Read the properties file from resource folder
        return new JasyptEncryptorConfigurationProperties();
    }

    public String getPassword() {
        return password;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getKeyObtentionIterations() {
        return keyObtentionIterations;
    }

    public String getPoolSize() {
        return poolSize;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getProviderClassName() {
        return providerClassName;
    }

    public String getSaltGeneratorClassname() {
        return saltGeneratorClassname;
    }

    public String getIvGeneratorClassname() {
        return ivGeneratorClassname;
    }

    public String getStringOutputType() {
        return stringOutputType;
    }
}
