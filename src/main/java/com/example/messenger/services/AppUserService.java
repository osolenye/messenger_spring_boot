package com.example.messenger.services;


import com.example.messenger.models.entities.AppUser;
import com.example.messenger.models.dto.AppUserCreateRequest;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> register(AppUserCreateRequest appUserCreateRequest);
}
