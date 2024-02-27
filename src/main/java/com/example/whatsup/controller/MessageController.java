package com.example.whatsup.controller;

import com.example.whatsup.dto.InstanceDTO;
import com.example.whatsup.dto.findmessage.ResponseFindMessageDTO;
import com.example.whatsup.dto.sendmessage.SendMessageDTO;
import com.example.whatsup.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<InstanceDTO> getMessages() {
        return this.messageService.getMessage();
    }

    /*
    @PostMapping
    public List<ResponseFindMessageDTO> findMessage() {
        return this.messageService.findMessage();
    }
     */

    @PostMapping()
    public void sendMessage() {
        this.messageService.sendMessage();
    }
}
