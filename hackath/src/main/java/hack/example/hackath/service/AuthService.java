package com.hackathon.healthportal.service;

import com.hackathon.healthportal.model.*;

public interface AuthService {
    String register(RegisterRequest req);
    String login(LoginRequest req);
}
