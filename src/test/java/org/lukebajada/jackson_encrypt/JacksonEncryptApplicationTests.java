package org.lukebajada.jackson_encrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lukebajada.jackson_encrypt.config.EncryptionConfig;
import org.lukebajada.jackson_encrypt.config.JacksonConfig;
import org.lukebajada.jackson_encrypt.models.Address;
import org.lukebajada.jackson_encrypt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@SpringBootTest(classes = {JacksonConfig.class, EncryptionConfig.class})
class JacksonEncryptApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void encryptionDecryptionTest() throws JsonProcessingException {
        User user = new User();
        user.setName("John Doe");
        user.setPassword("mySecretPassword");
        user.setAge(5);
        user.setAddress(new Address("New York City", "United States"));

        String json = objectMapper.writeValueAsString(user);
        user = objectMapper.readValue(json, User.class);

        Assertions.assertEquals(user, objectMapper.readValue(json, User.class));
    }

}
