package com.taskManager.taskManager.adapter.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.taskManager.taskManager.application.exception.UsernameAlreadyExistsException;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameExists() {
        return ResponseEntity.status(CONFLICT).body("Username already exists.");
    }
}
