package com.taskManager.taskManager.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taskManager.taskManager.application.exception.UsernameAlreadyExistsException;
import com.taskManager.taskManager.domain.model.User;
import com.taskManager.taskManager.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void registerAccount(String email, String password) {

        String username = email.toLowerCase();
        String encodedPassword = passwordEncoder.encode(password);
        String USER_AUTHORITY = "USER";

        User user = User.builder().username(username)
                .password(encodedPassword)
                .authority(USER_AUTHORITY)
                .build();

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }

        userRepository.save(user);
    }

}
