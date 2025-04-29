package com.taskManager.taskManager.adapter.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taskManager.taskManager.adapter.web.dto.AuthTokenTO;
import com.taskManager.taskManager.application.service.TokenService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/api/auth/token")
    public AuthTokenTO generateToken() {

        return AuthTokenTO.builder()
                .token(tokenService.generateToken())
                .build();
    }
}
