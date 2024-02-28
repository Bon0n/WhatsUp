package com.example.whatsup.dto.Instance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InstanceDTO(InstancePropDTO instance) {
}
