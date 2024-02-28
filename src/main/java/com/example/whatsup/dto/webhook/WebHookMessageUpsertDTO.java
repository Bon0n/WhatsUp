package com.example.whatsup.dto.webhook;

import com.example.whatsup.dto.findmessage.ResponseFindMessageKeyDTO;
import com.example.whatsup.dto.findmessage.ResponseFindMessageMessageDTO;

public record WebHookMessageUpsertDTO(
        String instance,
        ResponseWebHookMessageUpsertDataDTO data

) {
}
