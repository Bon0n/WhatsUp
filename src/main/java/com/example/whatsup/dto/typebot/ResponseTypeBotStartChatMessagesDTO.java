package com.example.whatsup.dto.typebot;

public record ResponseTypeBotStartChatMessagesDTO(
        String id,
        String type,
        ResponseTypeBotStartChatContentDTO content
) {
}
