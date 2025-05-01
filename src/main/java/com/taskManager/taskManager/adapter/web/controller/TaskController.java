package com.taskManager.taskManager.adapter.web.controller;

import com.taskManager.taskManager.adapter.web.dto.*;
import com.taskManager.taskManager.adapter.web.mapper.TaskAdapterMapper;
import com.taskManager.taskManager.application.service.TaskService;
import com.taskManager.taskManager.domain.model.Comment;
import com.taskManager.taskManager.domain.model.Task;
import com.taskManager.taskManager.domain.model.Task.TaskStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskAdapterMapper taskAdapterMapper;
    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseTO> getTasks(@RequestParam(required = false) String author,
                                         @RequestParam(required = false) String assignee) {
        List<Task> tasks = taskService.getTasks(author, assignee);
        return taskAdapterMapper.mapTasks(tasks);
    }

    @PostMapping
    public TaskResponseTO addTask(@RequestBody @Valid TaskRequestTO taskRequestTO) {
        Task task = taskService.addTask(taskRequestTO.getTitle(), taskRequestTO.getDescription());
        return taskAdapterMapper.map(task);
    }

    @PutMapping("/{taskId}/assign")
    public TaskResponseTO updateTaskAssignee(@PathVariable Long taskId, @RequestBody @Valid TaskAssignmentTO taskAssignmentTO) {
        Task task = taskService.updateTaskAssignee(taskId, taskAssignmentTO.getAssignee());
        return taskAdapterMapper.map(task);
    }

    @PutMapping("/{taskId}/status")
    public TaskResponseTO updateTaskStatus(@PathVariable Long taskId, @RequestBody @Valid TaskStatusTO taskStatusTO) {
        Task task = taskService.updateTaskStatus(taskId, TaskStatus.valueOf(taskStatusTO.getStatus().name()));
        return taskAdapterMapper.map(task);
    }

    @PostMapping("/{taskId}/comments")
    public void addComment(@PathVariable Long taskId, @RequestBody @Valid CommentRequestTO commentTO) {
        taskService.addComment(taskId, commentTO.getText());
    }

    @GetMapping("/{taskId}/comments")
    public List<CommentResponseTO> getTaskComments(@PathVariable Long taskId) {
        List<Comment> taskComments = taskService.getTaskComments(taskId);
        return taskAdapterMapper.mapComments(taskComments);
    }
}
