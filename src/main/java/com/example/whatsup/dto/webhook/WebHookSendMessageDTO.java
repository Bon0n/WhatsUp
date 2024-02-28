package com.example.whatsup.dto.webhook;

public record WebHookSendMessageDTO(
        String local,
        String url,
        String event
) {
}
