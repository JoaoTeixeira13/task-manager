package com.taskManager.taskManager.domain.dto;

import com.taskManager.taskManager.domain.model.Task.TaskStatus;

import java.time.Instant;

public interface TaskProjectionTO {
    Long getId();
    String getTitle();
    String getDescription();
    TaskStatus getStatus();
    String getAuthor();
    String getAssignee();
    Instant getCreatedDate();
    Long getTotalComments();
}
