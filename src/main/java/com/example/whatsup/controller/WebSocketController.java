package com.example.whatsup.controller;

import com.example.whatsup.dto.findmessage.ResponseFindMessageDTO;
import com.example.whatsup.dto.webhook.WebHookSendMessageDTO;
import com.example.whatsup.services.SendMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    private SendMessageService sendMessageService;
    public WebSocketController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }
    @GetMapping
    public void receive() {

    }

    @PostMapping(value = "/messages-update")
    public void send(@RequestBody ResponseFindMessageDTO responseFindMessageDTO) {
        System.out.print(responseFindMessageDTO);
        this.sendMessageService.sendMessage();
    }
}

