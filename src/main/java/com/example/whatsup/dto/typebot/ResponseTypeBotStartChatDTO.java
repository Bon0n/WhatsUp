package com.example.whatsup.dto.typebot;

import java.util.List;

public record ResponseTypeBotStartChatDTO(
        String sessionId,
        List<ResponseTypeBotStartChatMessagesDTO> messages

) {}
