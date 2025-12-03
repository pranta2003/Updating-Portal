package com.hackathon.healthportal.controller;

import com.hackathon.healthportal.model.*;
import com.hackathon.healthportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService auth;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return auth.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return auth.login(req);
    }
}
