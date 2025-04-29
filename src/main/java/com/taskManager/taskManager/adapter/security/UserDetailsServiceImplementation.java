package com.taskManager.taskManager.adapter.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.taskManager.taskManager.domain.model.User;
import com.taskManager.taskManager.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository
                .findByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return new UserAdapter(user);
    }
}
