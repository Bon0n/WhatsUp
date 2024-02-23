package com.example.whatsup.controller;

import com.example.whatsup.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public void getMessages() throws IOException {
        messageService.getMessages();
    }
}
