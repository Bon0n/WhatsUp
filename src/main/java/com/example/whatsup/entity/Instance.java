package com.example.whatsup.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class Instance {
    String name;
    String apiKey;
    boolean qrCode;
    int number;
}

/* 5519982803135 */