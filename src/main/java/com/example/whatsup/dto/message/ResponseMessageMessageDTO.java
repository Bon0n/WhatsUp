package com.example.whatsup.dto.message;

public record ResponseMessageMessageDTO(
        String conversation,
        ResponseMessageExtendedTextMessageDTO extendedTextMessage
        ) {
}
