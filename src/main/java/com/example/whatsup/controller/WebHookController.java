package com.example.whatsup.controller;

import com.example.whatsup.dto.findmessage.ResponseFindMessageDTO;
import com.example.whatsup.dto.webhook.WebHookMessageUpsertDTO;
import com.example.whatsup.services.SendMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    private final SendMessageService sendMessageService;

    public WebHookController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
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
        System.out.print(webHookMessageUpsert);
        if(!webHookMessageUpsert.data().key().fromMe() || webHookMessageUpsert.data().messageType().equalsIgnoreCase("audioMessage")) {
            if (webHookMessageUpsert.data().message().conversation().toLowerCase().contains("te amo"))
                this.sendMessageService.sendMessage("Te amo mucho",
                        webHookMessageUpsert.data().key().remoteJid().split("@")[0]);
        }
    }

    @PostMapping(value = "/chats-update")
    @ResponseStatus()
    public void chatUpdate(@RequestBody ResponseFindMessageDTO webHookSendMessageDTO) {
        System.out.print(webHookSendMessageDTO);
    }
}

