package com.example.whatsup.controller;

import com.example.whatsup.dto.message.ResponseMessageDTO;
import com.example.whatsup.dto.webhook.WebHookMessageUpsertDTO;
import com.example.whatsup.services.TypeBotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    private final TypeBotService typeBotService;

    public WebHookController(TypeBotService typeBotService) {
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
        this.typeBotService.redirect(webHookMessageUpsert);
    }

    @PostMapping(value = "/chats-update")
    @ResponseStatus()
    public void chatUpdate(@RequestBody ResponseMessageDTO webHookSendMessageDTO) {
        System.out.print(webHookSendMessageDTO);
    }

}

