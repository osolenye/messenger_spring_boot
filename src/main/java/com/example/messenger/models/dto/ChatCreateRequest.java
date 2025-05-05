package com.example.messenger.models.dto;

import lombok.Data;

@Data
public class ChatCreateRequest {
    private String chatName;
    private String chatDescription;

}
