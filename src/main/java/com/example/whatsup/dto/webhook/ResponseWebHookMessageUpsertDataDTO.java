package com.example.whatsup.dto.webhook;

import com.example.whatsup.dto.findmessage.ResponseFindMessageKeyDTO;
import com.example.whatsup.dto.findmessage.ResponseFindMessageMessageDTO;

public record ResponseWebHookMessageUpsertDataDTO(
        ResponseFindMessageKeyDTO key,
        String pushName,
        ResponseFindMessageMessageDTO message,
        String messageType,
        String owner,
        String source
) {}
