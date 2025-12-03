package com.hackathon.healthportal.service.impl;

import com.hackathon.healthportal.model.*;
import com.hackathon.healthportal.repository.UserRepository;
import com.hackathon.healthportal.security.JwtProvider;
import com.hackathon.healthportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;

    @Override
    public String register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            return "Email already exists";

        User u = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();

        userRepository.save(u);
        return "User registered";
    }

    @Override
    public String login(LoginRequest req) {
        User u = userRepository.findByEmail(req.getEmail())
                .orElse(null);

        if (u == null) return "User not found";
        if (!encoder.matches(req.getPassword(), u.getPassword())) return "Wrong password";

        return jwt.generateToken(u.getId(), u.getEmail(), u.getRole());
    }
}
