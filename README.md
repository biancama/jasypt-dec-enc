# Jasypt Enc Dec Tool
Simple convenient tool to encrypt and decrypt passwords using default jasypt properties.

## Build
`mvn clean compile`

## Run
`mvn exec:java`
There are 4 parameters
### Encrypt 1 password
`mvn exec:java -Dexec.args="-t ENC jasyptPwd secret"`
### Encrypt 1 file
The file must be in the format
```text
key1: pwd1InClear
key2: pwd2InClear
key3: pwd3InClear
  ....
```
To encrypt the whole file. Please use the absolute file name of the file
`mvn exec:java -Dexec.args="-t ENC-FILE jasyptPwd /TST/decrypted.txt"`

### Decrypt 1 password
`mvn exec:java -Dexec.args="-t DEC jasyptPwd ENC(04/tSw82QA3biTiFNmnf+68TgvnOK9z0zzTR9BeDG7Sz+1iR9Tl8iOImUusN29tM)"`
### Decrypt 1 file
The file must be in the format
```text
key1: pwd1Encrypted
key2: pwd2Encrypted
key3: pwd3Encrypted
  ....
```
To encrypt the whole file. Please use the absolute file name of the file
`mvn exec:java -Dexec.args="-t DEC-FILE jasyptPwd /TST/encrypted.txt"`

