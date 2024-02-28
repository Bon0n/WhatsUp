package com.example.whatsup.services;

import com.example.whatsup.configuration.TypeBotAPIConfiguration;
import com.example.whatsup.configuration.WhatsUpAPIConfiguration;
import com.example.whatsup.dto.message.ResponseMessageDTO;
import com.example.whatsup.dto.sendmessage.SendMessageDTO;
import com.example.whatsup.dto.sendmessage.TextMessageDTO;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatDTO;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatMessagesDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMessageService {
    private final RestTemplate restTemplate;
    private final WhatsUpAPIConfiguration whatsUpAPIConfiguration;
    private final TypeBotAPIConfiguration typeBotAPIConfiguration;

    public SendMessageService(
            RestTemplate restTemplate,
            WhatsUpAPIConfiguration whatsUpAPIConfiguration,
            TypeBotAPIConfiguration typeBotAPIConfiguration
    ) {
        this.restTemplate = restTemplate;
        this.whatsUpAPIConfiguration = whatsUpAPIConfiguration;
        this.typeBotAPIConfiguration = typeBotAPIConfiguration;
    }

    public void sendMessage(String message, String number) {
        TextMessageDTO text = new TextMessageDTO(message);
        SendMessageDTO sendMessage = new SendMessageDTO(number, text);

        HttpEntity<SendMessageDTO> whatsUpEntity = new HttpEntity<>(sendMessage, this.whatsUpAPIConfiguration.getHeaders());

        var response = new RestTemplate().postForObject("http://localhost:8080/message/sendText/Andrei", whatsUpEntity
                , ResponseMessageDTO.class);



    }
}
