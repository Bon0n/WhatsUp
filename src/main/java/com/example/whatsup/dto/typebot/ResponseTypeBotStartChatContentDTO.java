package com.example.whatsup.dto.typebot;

import java.util.List;

public record ResponseTypeBotStartChatContentDTO(
        List<ResponseTypeBotStartChatRichTextDTO> richText
) {
}
