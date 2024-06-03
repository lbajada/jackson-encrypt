package org.lukebajada.jackson_encrypt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.lukebajada.jackson_encrypt.serializers.modifiers.JsonEncryptDeserializerModifier;
import org.lukebajada.jackson_encrypt.serializers.modifiers.JsonEncryptSerializerModifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper basicObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ObjectMapper objectMapper(JsonEncryptSerializerModifier jsonEncryptSerializerModifier, JsonEncryptDeserializerModifier jsonEncryptDeserializerModifier) {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(jsonEncryptSerializerModifier);
        module.setDeserializerModifier(jsonEncryptDeserializerModifier);
        mapper.registerModule(module);

        return mapper;
    }
}
