package com.example.whatsup.dto.Instance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InstancePropDTO (
    String instanceName,
    String instanceId,
    String owner,
    String profileName,
    String profilePictureUrl,
    String profileStatus,
    String status,
    String serverUrl,
    String apiKey
) {

}


