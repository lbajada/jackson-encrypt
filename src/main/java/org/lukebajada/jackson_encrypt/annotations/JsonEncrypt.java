package org.lukebajada.jackson_encrypt.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonEncrypt {
}
