package com.example.whatsup.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
@Getter
public class TypeBotAPIConfiguration {
    private HttpHeaders headers = new HttpHeaders();
    public TypeBotAPIConfiguration(){
        this.headers.set("Authorization", "Bearer A6J9CXNLo1GXYqeYgLudVLnw");
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
