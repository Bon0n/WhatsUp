package com.example.whatsup.controller;

import com.example.whatsup.dto.message.ResponseMessageDTO;
import com.example.whatsup.dto.webhook.WebHookMessageUpsertDTO;
import com.example.whatsup.services.SendMessageService;
import com.example.whatsup.services.TypeBotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    private final SendMessageService sendMessageService;
    private final TypeBotService typeBotService;

    public WebHookController(SendMessageService sendMessageService, TypeBotService typeBotService) {
        this.sendMessageService = sendMessageService;
        this.typeBotService = typeBotService;
    }

    @GetMapping
    public void receive() {

    }

    @PostMapping(value = "/messages-update")
    public void send(@RequestBody WebHookMessageUpsertDTO webHookMessageUpsertDTO) {
        System.out.print(webHookMessageUpsertDTO);
    }

    @PostMapping(value = "/messages-upsert")
    public void messageUpsert(@RequestBody WebHookMessageUpsertDTO webHookMessageUpsert) {
        /*if(!webHookMessageUpsert.data().key().fromMe()) { */
            this.typeBotService.direct(webHookMessageUpsert);
            /*
            if(webHookMessageUpsert.data().messageType().equalsIgnoreCase("conversation")) {
                if (webHookMessageUpsert.data().message().conversation().toLowerCase().contains("te amo"))
                    this.sendMessageService.sendMessage("Te amo mucho",
                            webHookMessageUpsert.data().key().remoteJid().split("@")[0]);
                if (webHookMessageUpsert.data().message().conversation().toLowerCase().contains("evelin"))
                    this.sendMessageService.sendMessage("Evelin aquela vagbundaaaa burraaaaaaaaaaa",
                            webHookMessageUpsert.data().key().remoteJid().split("@")[0]);
            } else if(webHookMessageUpsert.data().messageType().equalsIgnoreCase("extendedTextMessage")) {
                if (webHookMessageUpsert.data().message().conversation().toLowerCase().contains("te amo"))
                    this.sendMessageService.sendMessage("Te amo mucho",
                            webHookMessageUpsert.data().key().remoteJid().split("@")[0]);
                if (webHookMessageUpsert.data().message().conversation().toLowerCase().contains("evelin"))
                    this.sendMessageService.sendMessage("Evelin aquela vagbundaaaa burraaaaaaaaaaa",
                            webHookMessageUpsert.data().key().remoteJid().split("@")[0]);
            }


        }
        */
    }

    @PostMapping(value = "/chats-update")
    @ResponseStatus()
    public void chatUpdate(@RequestBody ResponseMessageDTO webHookSendMessageDTO) {
        System.out.print(webHookSendMessageDTO);
    }

}

