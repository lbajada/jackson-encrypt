package org.lukebajada.jackson_encrypt.serializers.modifiers;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.lukebajada.jackson_encrypt.annotations.JsonEncrypt;
import org.lukebajada.jackson_encrypt.serializers.JsonEncryptSerializer;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

import java.util.List;

public class JsonEncryptSerializerModifier extends BeanSerializerModifier {

    private final BytesEncryptor bytesEncryptor;

    public JsonEncryptSerializerModifier(BytesEncryptor bytesEncryptor) {
        this.bytesEncryptor = bytesEncryptor;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        JsonEncryptSerializer jsonEncryptSerializer = new JsonEncryptSerializer(bytesEncryptor);

        for (BeanPropertyWriter writer : beanProperties) {
            if (writer.getAnnotation(JsonEncrypt.class) != null) {
                writer.assignSerializer(jsonEncryptSerializer);
            }
        }
        return beanProperties;
    }
}
