package com.example.messenger.controllers;


import com.example.messenger.models.dto.MessagingConnection;
import com.example.messenger.models.entities.Chat;
import com.example.messenger.repositories.ChatRepository;
import lombok.Data;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/messaging")
@Data
public class MessagingController {
    private final ChatRepository chatRepository;

    public MessagingController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @MessageMapping("/{chatId}")
    @SendTo("/topic/messages/{chatId}")
    public Chat chatting(@DestinationVariable Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }
}
