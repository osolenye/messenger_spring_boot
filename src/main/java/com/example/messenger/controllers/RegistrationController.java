package com.example.messenger.controllers;

import com.example.messenger.models.dto.AppUserCreateRequest;
import com.example.messenger.services.AppUserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
@Data
public class RegistrationController {
    private final AppUserService appUserService;
    @PostMapping("/")
    ResponseEntity<?> registration(@RequestBody AppUserCreateRequest appUserCreateRequest) {
        return ResponseEntity.ok(appUserService.register(appUserCreateRequest));
    }

}
