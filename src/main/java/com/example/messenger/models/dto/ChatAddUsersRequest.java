package com.example.messenger.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatAddUsersRequest {
    List<Long> usersId;
    Long chatId;
}
