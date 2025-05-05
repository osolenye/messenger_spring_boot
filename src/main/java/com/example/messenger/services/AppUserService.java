package com.example.messenger.services;


import com.example.messenger.models.entities.AppUser;
import com.example.messenger.models.requests.AppUserCreateRequest;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> register(AppUserCreateRequest appUserCreateRequest);
}
