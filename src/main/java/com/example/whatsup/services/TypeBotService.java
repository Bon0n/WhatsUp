package com.example.whatsup.services;

import com.example.whatsup.configuration.TypeBotAPIConfiguration;
import com.example.whatsup.dto.typebot.ResponseTypeBotStartChatDTO;
import com.example.whatsup.dto.typebot.TypeBotSendMessage;
import com.example.whatsup.dto.webhook.WebHookMessageUpsertDTO;
import org.javatuples.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeBotService {
    private final TypeBotAPIConfiguration typeBotAPIConfiguration;
    private final SendMessageService sendMessageService;
    private List<Pair<String, String>> userSessions = new ArrayList<>();

    public TypeBotService(TypeBotAPIConfiguration typeBotAPIConfiguration, SendMessageService sendMessageService) {
        this.typeBotAPIConfiguration = typeBotAPIConfiguration;
        this.sendMessageService = sendMessageService;
    }

    public void redirect(WebHookMessageUpsertDTO webHookMessageUpsert) {
        String message = getMessage(webHookMessageUpsert);
        String pushName = webHookMessageUpsert.data().pushName();
        String number = webHookMessageUpsert.data().key().remoteJid().split("@")[0];
        String sessionId = verifySession(pushName).getValue0();
        if (sessionId.isEmpty()) {
            if (message.equalsIgnoreCase("Começar")) {
                this.sendWelcomeMessage(pushName, number);
                List<String> messagesToSend = startChat(pushName);
                messagesToSend.forEach(m -> {
                    this.sendMessageService.sendMessage(m, number);
                });
            }
        } else {
            if(message.equalsIgnoreCase("sair")) {
                exitChat(pushName,number);
            } else {
                List<String> messagesToSend = continueChat(sessionId, message);
                messagesToSend.forEach(m -> this.sendMessageService.sendMessage(m, number));
            }

        }
    }

    public List<String> startChat(String pushName) {
        ResponseTypeBotStartChatDTO responseTypeBot = new ResponseTypeBotStartChatDTO("", new ArrayList<>());
        HttpEntity<ResponseTypeBotStartChatDTO> typeBotEntity = new HttpEntity<>(responseTypeBot, this.typeBotAPIConfiguration.getHeaders());
        ResponseTypeBotStartChatDTO typeBot = new RestTemplate().postForObject(this.typeBotAPIConfiguration.getUrlStart(),
                typeBotEntity, ResponseTypeBotStartChatDTO.class);
        List<String> textList = new ArrayList<>();
        assert typeBot != null;
        typeBot.messages().forEach(m -> m.
                content()
                .richText()
                .forEach(r -> r.children().forEach(c -> textList.add(c.text()))));
        this.userSessions.add(new Pair<>(typeBot.sessionId(), pushName));
        return textList;
    }

    private List<String> continueChat(String sessionId, String message) {
        HttpEntity<TypeBotSendMessage> typeBotEntity = new HttpEntity<>(new TypeBotSendMessage(message), this.typeBotAPIConfiguration.getHeaders());
        ResponseTypeBotStartChatDTO typeBot = new RestTemplate().postForObject("https://typebot.io/api/v1/sessions/" + sessionId + "/continueChat",
                typeBotEntity, ResponseTypeBotStartChatDTO.class);
        List<String> textList = new ArrayList<>();
        assert typeBot != null;
        typeBot.messages().forEach(m -> m
                .content().
                richText().
                forEach(r -> r.children().forEach(c -> textList.add(c.text()))));
        return textList;

    }

    private void exitChat(String pushName, String number) {
        Pair<String,String > userSession = verifySession(pushName);
        this.userSessions.remove(userSession);
        this.sendMessageService.sendMessage("Volte sempre " + pushName, number);
        this.sendMessageService.sendMessage("O chat foi finalizado. Para começar novamente, digite \"Começar\".", number);
    }

    private void sendWelcomeMessage(String pushName, String number) {
        this.sendMessageService.sendMessage("Olá " + pushName, number);
    }

    private String getMessage(WebHookMessageUpsertDTO webHookMessageUpsert) {
        if (webHookMessageUpsert.data().messageType().equalsIgnoreCase("conversation"))
            return webHookMessageUpsert.data().message().conversation();
        if (webHookMessageUpsert.data().messageType().equalsIgnoreCase("extendedTextMessage"))
            return webHookMessageUpsert.data().message().extendedTextMessage().text();
        return "";
    }

    private Pair<String, String> verifySession(String pushName) {
        try {
            return this.userSessions.stream()
                    .filter(session -> session.getValue1().equalsIgnoreCase(pushName))
                    .findFirst()
                    .orElse(new Pair<>("", ""));
        } catch (Exception e) {
            return new Pair<>("", "");
        }

    }


}
