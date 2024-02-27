package com.example.whatsup.services;

import com.example.whatsup.dto.InstanceDTO;
import com.example.whatsup.dto.findmessage.ResponseFindMessageDTO;
import com.example.whatsup.dto.sendmessage.SendMessageDTO;
import com.example.whatsup.dto.sendmessage.TextMessageDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MessageService {
    private RestTemplate restTemplate;
    public MessageService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<InstanceDTO> getMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "B6D711FCDE4D4FD5936544120E713976");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<InstanceDTO>> instance = restTemplate.exchange(
                "http://localhost:8080/instance/fetchInstances", HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                }
        );
        List<InstanceDTO> list = instance.getBody();
        return list;
    }

    public List<ResponseFindMessageDTO> findMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "B6D711FCDE4D4FD5936544120E713976");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("remoteJid", "5519982803135@s.whatsapp.net");
        HttpEntity<?> entity = new HttpEntity<>(body, headers);
        ResponseEntity<List<ResponseFindMessageDTO>> instance = restTemplate.exchange(
                "http://localhost:8080/chat/findMessages/Andrei", HttpMethod.POST, entity,
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                }
        );
        List<ResponseFindMessageDTO> list = instance.getBody();
        return list;
    }

    public void sendMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "B6D711FCDE4D4FD5936544120E713976");
        /*
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("number", "19982803135");
        body.add("textMessage", "Oi mozudaaaaaaaaaaaa");

         */
        HttpEntity<?> entity = new HttpEntity<>(headers);
        TextMessageDTO text = new TextMessageDTO("Oiiii mozudaaaaaaaa");
        SendMessageDTO sendMessage = new SendMessageDTO("19982803135", text);
        restTemplate.exchange(
                "http://localhost:8080/message/sendText/Andrei",
                HttpMethod.POST,
                entity,
                SendMessageDTO.class
        );
    }

}
