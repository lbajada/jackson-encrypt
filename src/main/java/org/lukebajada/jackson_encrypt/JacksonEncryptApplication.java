package org.lukebajada.jackson_encrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lukebajada.jackson_encrypt.models.Address;
import org.lukebajada.jackson_encrypt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JacksonEncryptApplication implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(JacksonEncryptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setPassword("mySecretPassword");
        user.setAge(5);
        user.setAddress(new Address("New York City", "United States"));

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }

}
