package com.example.whatsup.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
@Getter
public class WhatsUpAPIConfiguration {
    private HttpHeaders headers = new HttpHeaders();
    public WhatsUpAPIConfiguration(){
        this.headers.set("apikey", "B6D711FCDE4D4FD5936544120E713976");
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
