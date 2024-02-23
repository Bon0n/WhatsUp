package com.example.whatsup.service;

import com.example.whatsup.resource.HttpConnection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class MessageService {
    private final URL urlMessages = new URL("localhost:8080/chat/findMessages/Andrei");
    HttpConnection httpConnection;
    public MessageService(HttpConnection httpConnection) throws MalformedURLException {
        this.httpConnection = httpConnection;
    }

    public void getMessages() throws IOException {
        httpConnection.connect(urlMessages);
    }
}
