package com.taskManager.taskManager.adapter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {
    @NotBlank
    @Email
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;
}
