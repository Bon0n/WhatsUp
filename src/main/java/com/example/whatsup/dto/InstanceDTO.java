package com.example.whatsup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InstanceDTO(InstancePropDTO instance) {
}
