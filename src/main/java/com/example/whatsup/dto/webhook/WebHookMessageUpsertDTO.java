package com.example.whatsup.dto.webhook;

public record WebHookMessageUpsertDTO(
        String instance,
        ResponseWebHookMessageUpsertDataDTO data

) {
}
