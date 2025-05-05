package com.example.messenger.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserCreateRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
