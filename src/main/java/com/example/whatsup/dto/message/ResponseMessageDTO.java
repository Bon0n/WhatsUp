package com.example.whatsup.dto.message;

public record ResponseMessageDTO(
        ResponseMessageKeyDTO key,
        String pushName,
        ResponseMessageMessageDTO message,
        String messageType,
        String owner,
        String source
) {
}
