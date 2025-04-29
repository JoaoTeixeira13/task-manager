package com.taskManager.taskManager.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CommentResponseTO {
    private String id;
    private String task_id;
    private String text;
    private String author;
}
