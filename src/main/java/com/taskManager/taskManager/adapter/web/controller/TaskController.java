package com.taskManager.taskManager.adapter.web.controller;

import com.taskManager.taskManager.application.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.taskManager.taskManager.adapter.web.dto.*;
import com.taskManager.taskManager.adapter.web.dto.TaskResponseTO.TaskStatusTE;
import com.taskManager.taskManager.domain.model.Comment;
import com.taskManager.taskManager.domain.model.Task;
import com.taskManager.taskManager.domain.model.Task.TaskStatus;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/api/tasks")
    public List<TaskResponseTO> getTasks(@RequestParam(required = false) String author,
                                         @RequestParam(required = false) String assignee) {

        List<Task> tasks = taskService.getTasks(author, assignee);

        return tasks.stream().map(task -> TaskResponseTO.builder()
                .id(task.getId().toString())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatusTE.valueOf(task.getStatus().name()))
                .author(task.getAuthor())
                .assignee(task.getAssignee())
                .totalComments(task.getTotalComments())
                .build()).toList();
    }

    @PostMapping("/api/tasks")
    public TaskResponseTO addTask(@RequestBody @Valid TaskRequestTO taskRequestTO) {
        Task task = taskService.addTask(taskRequestTO.getTitle(), taskRequestTO.getDescription());

        return TaskResponseTO.builder()
                .id(task.getId().toString())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatusTE.valueOf(task.getStatus().name()))
                .author(task.getAuthor())
                .assignee(task.getAssignee())
                .build();
    }

    @PutMapping("/api/tasks/{taskId}/assign")
    public TaskResponseTO updateTaskAssignee(@PathVariable Long taskId, @RequestBody @Valid TaskAssignmentTO taskAssignmentTO) {
        Task task = taskService.updateTaskAssignee(taskId, taskAssignmentTO.getAssignee());

        return TaskResponseTO.builder()
                .id(task.getId().toString())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatusTE.valueOf(task.getStatus().name()))
                .author(task.getAuthor())
                .assignee(task.getAssignee())
                .build();
    }


    @PutMapping("/api/tasks/{taskId}/status")
    public TaskResponseTO updateTaskStatus(@PathVariable Long taskId, @RequestBody @Valid TaskStatusTO taskStatusTO) {

        Task task = taskService.updateTaskStatus(taskId, TaskStatus.valueOf(taskStatusTO.getStatus().name()));

        return TaskResponseTO.builder()
                .id(task.getId().toString())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatusTE.valueOf(task.getStatus().name()))
                .author(task.getAuthor())
                .assignee(task.getAssignee())
                .build();
    }

    @PostMapping("/api/tasks/{taskId}/comments")
    public void addComment(@PathVariable Long taskId, @RequestBody @Valid CommentRequestTO commentTO) {

        taskService.addComment(taskId, commentTO.getText());
    }

    @GetMapping("/api/tasks/{taskId}/comments")
    public List<CommentResponseTO> getTaskComments(@PathVariable Long taskId) {

        List<Comment> taskComments = taskService.getTaskComments(taskId);

        return taskComments.stream().map(comment -> CommentResponseTO.builder()
                .id(comment.getId().toString())
                .task_id(comment.getTaskId().toString())
                .text(comment.getText())
                .author(comment.getAuthor())
                .build()).toList();

    }

}
