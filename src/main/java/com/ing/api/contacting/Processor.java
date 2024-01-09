package com.ing.api.contacting;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.ing.api.contacting.JasyptEncryptorConfigurationProperties.createProperties;
import static java.lang.String.format;

@CommandLine.Command(name = "enc-dev", mixinStandardHelpOptions = true, version = "1.0",
        description = "Prints the encrypted or decrypted password to STDOUT.")
public class Processor implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "jasypt password")
    private String jasyptPwd;

    @CommandLine.Parameters(index = "1", description = "password to encrypt or decrypt or absolute path of the file to encrypt or decrypt.")
    private String pwd;
    @CommandLine.Option(names = {"-t", "--type"}, description = "ENC, DEC, ENC-FILE, DEC-FILE")
    private String type = "ENC";
    @Override
    public Integer call() throws Exception {
        switch (type) {
            case "ENC" :
                encrypt(jasyptPwd, pwd);
                break;
            case "DEC" :
                decrypt(jasyptPwd, pwd);
                break;
            case "DEC-FILE" :
                decryptFile(jasyptPwd, pwd);
                break;
            case "ENC-FILE" :
                encryptFile(jasyptPwd, pwd);
                break;
            default:
                return 0;
        }

        return 0;
    }

    private void printSeparator() {
        System.out.println("#######################################################################################################");
    }


    private void encrypt(String jasyptPwd, String pwd) {
        var dec = getEncDec(jasyptPwd);
        printSeparator();
        System.out.printf("%s", dec.encrypt(pwd));
    }


    private void decrypt(String jasyptPwd, String pwd) {
        var dec = getEncDec(jasyptPwd);
        printSeparator();
        System.out.printf("%s", dec.decrypt(pwd));
    }

    private void decryptFile(String jasyptPwd, String filename) {
        var dec = getEncDec(jasyptPwd);
        encryptDecryptFile(jasyptPwd, filename, s -> dec.decrypt(s));
    }

    private void encryptFile(String jasyptPwd, String filename) {
        var dec = getEncDec(jasyptPwd);
        encryptDecryptFile(jasyptPwd, filename, s -> dec.encrypt(s));
    }

    private void encryptDecryptFile(String jasyptPwd, String filename, Function<String, String> fun) {
        printSeparator();
        var fileReader = new FileReader(filename);
        for (var entry : fileReader.getEntries()) {
            System.out.printf("%s: %s\n", entry.getValue0(), fun.apply(entry.getValue1()));
        }
        System.out.println();
    }
    private static EncDec getEncDec(String jasyptPwd) {
        var stringEncryptor = createPBEDefault(jasyptPwd, createProperties());

        var dec = new EncDec(stringEncryptor);
        return dec;
    }

    private static <T> T get(Supplier<T> supplier, String key, T defaultValue) {
        T value = supplier.get();
        if (value == defaultValue) {
            System.out.println(format("Encryptor config not found for property %s, using default value: %s", key, value));
        }
        return value;
    }


    private static StringEncryptor createPBEDefault(String jasyptPwd, JasyptEncryptorConfigurationProperties configProps) {
        final String propertyPrefix ="jasypt.encryptor";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(jasyptPwd);
        config.setAlgorithm(get(configProps::getAlgorithm, propertyPrefix + ".algorithm", "PBEWITHHMACSHA512ANDAES_256"));
        config.setKeyObtentionIterations(get(configProps::getKeyObtentionIterations, propertyPrefix + ".key-obtention-iterations", "1000"));
        config.setPoolSize(get(configProps::getPoolSize, propertyPrefix + ".pool-size", "1"));
        config.setProviderName(get(configProps::getProviderName, propertyPrefix + ".provider-name", null));
        config.setProviderClassName(get(configProps::getProviderClassName, propertyPrefix + ".provider-class-name", null));
        config.setSaltGeneratorClassName(get(configProps::getSaltGeneratorClassname, propertyPrefix + ".salt-generator-classname", "org.jasypt.salt.RandomSaltGenerator"));
        config.setIvGeneratorClassName(get(configProps::getIvGeneratorClassname, propertyPrefix + ".iv-generator-classname", "org.jasypt.iv.RandomIvGenerator"));
        config.setStringOutputType(get(configProps::getStringOutputType, propertyPrefix + ".string-output-type", "base64"));
        encryptor.setConfig(config);
        return encryptor;
    }
}
