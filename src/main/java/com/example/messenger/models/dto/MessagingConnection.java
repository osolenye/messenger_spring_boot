package com.example.messenger.models.dto;

import lombok.Data;

@Data
public class MessagingConnection {
    private String chatId;
    private String jwtToken;
}
