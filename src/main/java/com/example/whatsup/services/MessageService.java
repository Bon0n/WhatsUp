package com.example.whatsup.services;

import com.example.whatsup.configuration.WhatsUpAPIConfiguration;
import com.example.whatsup.dto.Instance.InstanceDTO;
import com.example.whatsup.dto.message.ResponseMessageDTO;
import com.example.whatsup.dto.sendmessage.SendMessageDTO;
import com.example.whatsup.dto.sendmessage.TextMessageDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MessageService {
    private RestTemplate restTemplate;
    private WhatsUpAPIConfiguration whatsUpAPIConfiguration;
    public MessageService(RestTemplate restTemplate, WhatsUpAPIConfiguration whatsUpAPIConfiguration)
    {
        this.restTemplate = restTemplate;
        this.whatsUpAPIConfiguration = whatsUpAPIConfiguration;
    }

    public List<InstanceDTO> getInstance() {
        HttpEntity<String> entity = new HttpEntity<>(this.whatsUpAPIConfiguration.getHeaders());
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

    public List<ResponseMessageDTO> findMessage() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("remoteJid", "5519982803135@s.whatsapp.net");
        HttpEntity<?> entity = new HttpEntity<>(body, this.whatsUpAPIConfiguration.getHeaders());
        ResponseEntity<List<ResponseMessageDTO>> instance = restTemplate.exchange(
                "http://localhost:8080/chat/findMessages/Andrei", HttpMethod.POST, entity,
                new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                }
        );
        List<ResponseMessageDTO> list = instance.getBody();
        return list;
    }

    public void sendMessage() {
        TextMessageDTO text = new TextMessageDTO("Oiiii mozudaaaaaaaa");
        SendMessageDTO sendMessage = new SendMessageDTO("19982803135", text);

        HttpEntity<SendMessageDTO> entity = new HttpEntity<>(sendMessage, this.whatsUpAPIConfiguration.getHeaders());

        var response = new RestTemplate().postForObject("http://localhost:8080/message/sendText/Andrei", entity
        , ResponseMessageDTO.class);
    }

}
