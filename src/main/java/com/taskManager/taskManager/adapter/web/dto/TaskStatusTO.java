package com.taskManager.taskManager.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.taskManager.taskManager.adapter.web.dto.TaskResponseTO.TaskStatusTE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusTO {
    private TaskStatusTE status;
}
