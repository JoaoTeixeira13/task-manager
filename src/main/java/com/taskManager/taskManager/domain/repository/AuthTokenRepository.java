package com.taskManager.taskManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskManager.taskManager.domain.model.AuthToken;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
    Optional<AuthToken> findByToken(String token);
}
