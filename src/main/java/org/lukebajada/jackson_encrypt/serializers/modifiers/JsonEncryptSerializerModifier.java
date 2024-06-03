package org.lukebajada.jackson_encrypt.serializers.modifiers;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.lukebajada.jackson_encrypt.annotations.JsonEncrypt;
import org.lukebajada.jackson_encrypt.serializers.JsonEncryptSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonEncryptSerializerModifier extends BeanSerializerModifier {

    private final JsonEncryptSerializer jsonEncryptSerializer;

    public JsonEncryptSerializerModifier(JsonEncryptSerializer jsonEncryptSerializer) {
        this.jsonEncryptSerializer = jsonEncryptSerializer;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            if (writer.getAnnotation(JsonEncrypt.class) != null) {
                writer.assignSerializer(jsonEncryptSerializer);
            }
        }
        return beanProperties;
    }
}
