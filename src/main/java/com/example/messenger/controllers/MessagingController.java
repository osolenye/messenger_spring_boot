package com.example.messenger.controllers;


import com.example.messenger.models.dto.MessagingConnection;
import com.example.messenger.models.entities.Chat;
import com.example.messenger.models.entities.Message;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.MessageRepository;
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
    private final MessageRepository messageRepository;

    public MessagingController(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/{chatId}")
    @SendTo("/topic/messages/{chatId}")
    public List<Message> chatting(@DestinationVariable Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }
}
