package org.lukebajada.jackson_encrypt.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

import java.io.IOException;

public class JsonEncryptSerializer extends JsonSerializer<Object> {

    private final BytesEncryptor bytesEncryptor;

    private final ObjectMapper objectMapper;

    public JsonEncryptSerializer(BytesEncryptor bytesEncryptor) {
        this.bytesEncryptor = bytesEncryptor;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            byte[] encrypted = bytesEncryptor.encrypt(objectMapper.writeValueAsBytes(value));
            jsonGenerator.writeBinary(encrypted);
        } catch (IOException e) {
            throw new IOException("Error encrypting value", e);
        }
    }
}
