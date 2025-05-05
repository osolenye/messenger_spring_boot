package com.example.messenger.services.impl;

import com.example.messenger.models.entities.AppUser;
import com.example.messenger.models.requests.AppUserCreateRequest;
import com.example.messenger.repositories.AppUserRepository;
import com.example.messenger.services.AppUserService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppUser> register(AppUserCreateRequest appUserCreateRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserCreateRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserCreateRequest.getPassword()));
        appUser.setEmail(appUserCreateRequest.getEmail());
        appUser.setPhone(appUserCreateRequest.getPhone());
        appUser.setFirstName(appUserCreateRequest.getFirstName());
        appUser.setLastName(appUserCreateRequest.getLastName());
        // Save the user to the database
        AppUser savedUser = appUserRepository.save(appUser);
        // Return the saved user
        return Optional.of(savedUser);
    }
}
