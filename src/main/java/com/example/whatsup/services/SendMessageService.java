package com.example.whatsup.services;

import com.example.whatsup.configuration.WhatsUpAPIConfiguration;
import com.example.whatsup.dto.Instance.InstanceDTO;
import com.example.whatsup.dto.findmessage.ResponseFindMessageDTO;
import com.example.whatsup.dto.sendmessage.SendMessageDTO;
import com.example.whatsup.dto.sendmessage.TextMessageDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SendMessageService {
    private RestTemplate restTemplate;
    private WhatsUpAPIConfiguration whatsUpAPIConfiguration;
    public SendMessageService(RestTemplate restTemplate, WhatsUpAPIConfiguration whatsUpAPIConfiguration)
    {
        this.restTemplate = restTemplate;
        this.whatsUpAPIConfiguration = whatsUpAPIConfiguration;
    }

    public void sendMessage() {
        TextMessageDTO text = new TextMessageDTO("te amo te amo te amo");
        SendMessageDTO sendMessage = new SendMessageDTO("19982803135", text);

        HttpEntity<SendMessageDTO> entity = new HttpEntity<>(sendMessage, this.whatsUpAPIConfiguration.getHeaders());

        while(true) {
            var response = new RestTemplate().postForObject("http://localhost:8080/message/sendText/Andrei", entity
                    , ResponseFindMessageDTO.class);
        }

    }

}
