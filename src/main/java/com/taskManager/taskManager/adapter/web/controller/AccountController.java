package com.taskManager.taskManager.adapter.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.taskManager.taskManager.adapter.web.dto.UserTO;
import com.taskManager.taskManager.application.service.AccountService;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api/accounts")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid UserTO userTO) {

        accountService.registerAccount(userTO.getEmail(), userTO.getPassword());
        return ResponseEntity.ok().build();
    }
}
