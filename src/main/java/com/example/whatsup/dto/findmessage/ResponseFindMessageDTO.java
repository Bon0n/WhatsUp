package com.example.whatsup.dto.findmessage;

public record ResponseFindMessageDTO(
        ResponseFindMessageKeyDTO key,
        String pushName,
        ResponseFindMessageMessageDTO message,
        String messageType,
        String owner,
        String source
) {
}
