package org.lukebajada.jackson_encrypt.serializers.modifiers;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import org.lukebajada.jackson_encrypt.annotations.JsonEncrypt;
import org.lukebajada.jackson_encrypt.serializers.JsonEncryptDeserializer;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

public class JsonEncryptDeserializerModifier extends BeanDeserializerModifier {
    private final BytesEncryptor bytesEncryptor;

    private final ObjectMapper objectMapper;

    public JsonEncryptDeserializerModifier(BytesEncryptor bytesEncryptor) {
        this.bytesEncryptor = bytesEncryptor;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public BeanDeserializerBuilder updateBuilder(final DeserializationConfig config, final BeanDescription beanDescription, final BeanDeserializerBuilder builder) {
        var it = builder.getProperties();
        while (it.hasNext()) {
            var property = it.next();
            if (null != property.getAnnotation(JsonEncrypt.class)) {
                builder.addOrReplaceProperty(property.withValueDeserializer(new JsonEncryptDeserializer(bytesEncryptor, objectMapper, property.getType())), true);
            }
        }
        return builder;
    }
}
