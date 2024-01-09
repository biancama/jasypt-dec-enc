package com.ing.api.contacting;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import picocli.CommandLine;

import java.util.concurrent.Callable;
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
            default:
                return 0;
        }

        return 0;
    }

    private void encrypt(String jasyptPwd, String pwd) {
        var dec = getEncDec(jasyptPwd);
        System.out.printf("%s", dec.encrypt(pwd));
    }

    private void decrypt(String jasyptPwd, String pwd) {
        var dec = getEncDec(jasyptPwd);
        System.out.printf("%s", dec.decrypt(pwd));
    }

    private static EncDec getEncDec(String jasyptPwd) {
        var stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(jasyptPwd);
        var dec = new EncDec(stringEncryptor);
        return dec;
    }
}
