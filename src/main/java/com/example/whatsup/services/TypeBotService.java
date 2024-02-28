package com.example.whatsup.services;

import com.example.whatsup.configuration.TypeBotAPIConfiguration;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatChildrenDTO;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatDTO;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatMessagesDTO;
import com.example.whatsup.dto.webhook.WebHookMessageUpsertDTO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeBotService {
    private final TypeBotAPIConfiguration typeBotAPIConfiguration;
    private final SendMessageService sendMessageService;

    public TypeBotService(TypeBotAPIConfiguration typeBotAPIConfiguration, SendMessageService sendMessageService)
    {
        this.typeBotAPIConfiguration = typeBotAPIConfiguration;
        this.sendMessageService = sendMessageService;
    }

    public void direct(WebHookMessageUpsertDTO webHookMessageUpsert) {
            String message = getMessage(webHookMessageUpsert);
            String number = webHookMessageUpsert.data().key().remoteJid().split("@")[0];
            if(message.equalsIgnoreCase("Começar")){
                List<String> messagesToSend = startChat();
                messagesToSend.forEach(m -> {
                    this.sendMessageService.sendMessage(m, number);
                });
            }
    }

    public List<String> startChat(){
            ResponseTypeBotStartChatDTO responseTypeBot = new ResponseTypeBotStartChatDTO("",new ArrayList<ResponseTypeBotStartChatMessagesDTO>());
            HttpEntity<ResponseTypeBotStartChatDTO> typeBotEntity = new HttpEntity<>(responseTypeBot, this.typeBotAPIConfiguration.getHeaders());
            ResponseTypeBotStartChatDTO typeBot = new RestTemplate().postForObject("https://typebot.io/api/v1/typebots/clt5y2jjh000kp1fpr663j305/preview/startChat",
                    typeBotEntity, ResponseTypeBotStartChatDTO.class);
            List<String> textList = new ArrayList<>();
        assert typeBot != null;
        typeBot.messages().forEach(m -> {
                m.content().richText().forEach(r -> {
                    r.children().forEach(c -> {
                        textList.add(c.text());
                    });
                });
            });

        return textList;
    }

    private String getMessage(WebHookMessageUpsertDTO webHookMessageUpsert) {
        if(webHookMessageUpsert.data().messageType().equalsIgnoreCase("conversation"))
            return webHookMessageUpsert.data().message().conversation();
        if(webHookMessageUpsert.data().messageType().equalsIgnoreCase("extendedTextMessage"))
            return webHookMessageUpsert.data().message().extendedTextMessage().text();
        return "";
    }

}
