package com.example.whatsup.dto.webhook;
import com.example.whatsup.dto.message.ResponseMessageKeyDTO;
import com.example.whatsup.dto.message.ResponseMessageMessageDTO;

public record ResponseWebHookMessageUpsertDataDTO(
        ResponseMessageKeyDTO key,
        String pushName,
        ResponseMessageMessageDTO message,
        String messageType,
        String owner,
        String source
) {}
