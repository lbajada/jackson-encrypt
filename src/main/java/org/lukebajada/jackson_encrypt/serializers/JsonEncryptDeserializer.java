package org.lukebajada.jackson_encrypt.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class JsonEncryptDeserializer extends JsonDeserializer<Object> {

    private final BytesEncryptor bytesEncryptor;

    private final JavaType propertyType;

    private final ObjectMapper objectMapper;

    public JsonEncryptDeserializer(BytesEncryptor bytesEncryptor, ObjectMapper objectMapper, JavaType propertyType) {
        this.bytesEncryptor = bytesEncryptor;
        this.objectMapper = objectMapper;
        this.propertyType = propertyType;
    }


    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            byte[] decryptedBytes = bytesEncryptor.decrypt(jsonParser.getBinaryValue());
            return objectMapper.readValue(decryptedBytes, propertyType);
        } catch (IOException e) {
            throw new IOException("Error decrypting value", e);
        }
    }
}
