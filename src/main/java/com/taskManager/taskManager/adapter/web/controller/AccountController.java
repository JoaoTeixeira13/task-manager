package com.taskManager.taskManager.adapter.web.controller;

import com.taskManager.taskManager.adapter.web.dto.UserTO;
import com.taskManager.taskManager.application.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void registerAccount(@RequestBody @Valid UserTO userTO) {

        accountService.registerAccount(userTO.getEmail(), userTO.getPassword());
    }
}
