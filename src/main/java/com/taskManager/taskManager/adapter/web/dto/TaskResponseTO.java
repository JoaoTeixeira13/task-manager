package com.taskManager.taskManager.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseTO {
    private String id;
    private String title;
    private String description;
    private TaskStatusTE status;
    private String author;
    private String assignee;
    private Integer total_comments;

    public enum TaskStatusTE {
        COMPLETED,
        CREATED,
        IN_PROGRESS
    }
}


