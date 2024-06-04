package org.lukebajada.jackson_encrypt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@TestConfiguration
public class EncryptionConfig {

    @Bean
    public TextEncryptor textEncryptor(@Value("${encryption.password}") String password,
                                       @Value("${encryption.salt}") String salt) {
        return Encryptors.delux(password, salt);
    }

    @Bean
    public BytesEncryptor bytesEncryptor(@Value("${encryption.password}") String password,
                                         @Value("${encryption.salt}") String salt) {
        return Encryptors.stronger(password, salt);
    }
}
