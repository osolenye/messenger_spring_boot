package com.example.messenger.services;

import com.example.messenger.dto.AuthRequest;
import com.example.messenger.dto.RegisterRequest;
import com.example.messenger.models.Role;
import com.example.messenger.models.User;
import com.example.messenger.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtService.generateToken(user);
    }
}
