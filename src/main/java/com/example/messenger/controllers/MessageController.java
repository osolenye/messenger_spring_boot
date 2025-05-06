package com.example.messenger.controllers;

import com.example.messenger.models.dto.MessageCreateRequest;
import com.example.messenger.models.entities.AppUser;
import com.example.messenger.models.entities.Chat;
import com.example.messenger.models.entities.Message;
import com.example.messenger.repositories.AppUserRepository;
import com.example.messenger.repositories.ChatRepository;
import com.example.messenger.repositories.MessageRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@Data
public class MessageController {
    private final MessageRepository messageRepository;
    private final AppUserRepository appUserRepository;
    private final ChatRepository chatRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestBody MessageCreateRequest messageCreateRequest) {
        // Logic to create a message
        Message message = new Message();
        AppUser sender = appUserRepository.findById(messageCreateRequest.getSenderId()).orElseThrow(() -> new RuntimeException("Sender not found"));
        message.setSender(sender);
        Message answeredTo = messageRepository.findById(messageCreateRequest.getMessageAnsweredToId()).orElse(null);
        if (answeredTo != null) {
            message.setAnsweredTo(answeredTo);
        } else {
            message.setAnsweredTo(null);
        }
        Chat chat = chatRepository.findById(messageCreateRequest.getChatId()).orElseThrow(() -> new RuntimeException("Chat not found"));
        message.setChat(chat);
        message.setMessage(messageCreateRequest.getMessage());
        return ResponseEntity.ok(messageRepository.save(message));
    }
}
