package com.example.messenger.models.dto;

import lombok.Data;

@Data
public class MessageCreateRequest {
    private String message;
    private Long senderId;
    private Long messageAnsweredToId;
    private Long chatId;
}
