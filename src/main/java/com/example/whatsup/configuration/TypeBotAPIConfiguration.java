package com.example.whatsup.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
@Getter
public class TypeBotAPIConfiguration {
    private HttpHeaders headers = new HttpHeaders();
    private String urlStart = "https://typebot.io/api/v1/typebots/clt5y2jjh000kp1fpr663j305/preview/startChat";
    public TypeBotAPIConfiguration(){
        this.headers.set("Authorization", "Bearer A6J9CXNLo1GXYqeYgLudVLnw");
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
