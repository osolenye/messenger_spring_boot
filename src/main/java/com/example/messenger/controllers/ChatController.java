package com.example.messenger.controllers;

import com.example.messenger.models.dto.ChatCreateRequest;
import com.example.messenger.models.entities.Chat;
import com.example.messenger.repositories.ChatRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/chat")
@Data
public class ChatController {
    private final ChatRepository chatRepository;

    @PostMapping("/create")
    ResponseEntity<?> createChat(@RequestBody ChatCreateRequest chatCreateRequest) {
        // Logic to create a chat
        Chat chat = new Chat();
        chat.setChatName(chatCreateRequest.getChatName());
        chat.setChatDescription(chatCreateRequest.getChatDescription());
        chat.setUsers(new ArrayList<>());
        return ResponseEntity.ok(chatRepository.save(chat));
    }
}
