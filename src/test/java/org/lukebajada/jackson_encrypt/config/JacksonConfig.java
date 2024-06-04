package org.lukebajada.jackson_encrypt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.lukebajada.jackson_encrypt.serializers.modifiers.JsonEncryptDeserializerModifier;
import org.lukebajada.jackson_encrypt.serializers.modifiers.JsonEncryptSerializerModifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

@TestConfiguration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(BytesEncryptor bytesEncryptor) {
        ObjectMapper mapper = new ObjectMapper();

        JsonEncryptSerializerModifier jsonEncryptSerializerModifier = new JsonEncryptSerializerModifier(bytesEncryptor);
        JsonEncryptDeserializerModifier jsonEncryptDeserializerModifier = new JsonEncryptDeserializerModifier(bytesEncryptor);

        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(jsonEncryptSerializerModifier);
        module.setDeserializerModifier(jsonEncryptDeserializerModifier);
        mapper.registerModule(module);

        return mapper;
    }
}
