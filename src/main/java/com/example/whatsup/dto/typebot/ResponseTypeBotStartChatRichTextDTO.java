package com.example.whatsup.dto.typebot;

import java.util.List;

public record ResponseTypeBotStartChatRichTextDTO(
        List<ResponseTypeBotStartChatChildrenDTO> children
) {
}
