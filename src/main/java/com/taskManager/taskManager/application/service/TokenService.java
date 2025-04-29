package com.taskManager.taskManager.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taskManager.taskManager.domain.model.AuthToken;
import com.taskManager.taskManager.domain.repository.AuthTokenRepository;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static com.taskManager.taskManager.common.security.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthTokenRepository authTokenRepository;

    @Transactional
    public String generateToken() {
        byte[] bytes = KeyGenerators.secureRandom(24).generateKey();
        String token = new BigInteger(1, bytes).toString(16);
        Instant expiration = Instant.now().plus(Duration.ofHours(1));

        authTokenRepository.save(AuthToken.builder()
                .token(token)
                .username(getCurrentUsername())
                .expiresAt(expiration)
                .build());
        return token;
    }

    public Optional<String> getUsernameFromToken(String token) {
        return authTokenRepository.findByToken(token)
                .filter(authToken -> authToken.getExpiresAt().isAfter(Instant.now()))
                .map(AuthToken::getUsername);
    }
}
