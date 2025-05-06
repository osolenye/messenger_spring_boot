package com.example.messenger.controllers;

import com.example.messenger.models.dto.ChatAddUsersRequest;
import com.example.messenger.models.dto.ChatCreateRequest;
import com.example.messenger.models.entities.AppUser;
import com.example.messenger.models.entities.Chat;
import com.example.messenger.repositories.AppUserRepository;
import com.example.messenger.repositories.ChatRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@Data
public class ChatController {
    private final ChatRepository chatRepository;
    private final AppUserRepository appUserRepository;

    @PostMapping("/create")
    ResponseEntity<?> createChat(@RequestBody ChatCreateRequest chatCreateRequest,
                                 Authentication authentication) {
        // Logic to create a chat
        Chat chat = new Chat();
        chat.setChatName(chatCreateRequest.getChatName());
        chat.setChatDescription(chatCreateRequest.getChatDescription());
        AppUser chatCreator = appUserRepository.findByUsername(authentication.getName()).orElse(null);
        List<AppUser> users = new ArrayList<>();
        users.add(chatCreator);
        chat.setUsers(users);
        return ResponseEntity.ok(chatRepository.save(chat));
    }

    @PostMapping("/add/users")
    ResponseEntity<?> addUsersToChat(@RequestBody ChatAddUsersRequest chatAddUsersRequest) {
        Chat chat = chatRepository.findById(chatAddUsersRequest.getChatId()).orElse(null);
        List<AppUser> users = chat.getUsers();
        chatAddUsersRequest.getUsersId().forEach(usersId -> {
            AppUser user = appUserRepository.findById(usersId).orElse(null);
            if (user != null && !users.contains(user)) {
                users.add(user);
            }
        });
        chat.setUsers(users);
        return ResponseEntity.ok(chatRepository.save(chat));
    }
}
