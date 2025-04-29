package com.taskManager.taskManager.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.taskManager.taskManager.common.validation.EmailOrNone;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignmentTO {
    @NotBlank
    @EmailOrNone
    private String assignee;
}
