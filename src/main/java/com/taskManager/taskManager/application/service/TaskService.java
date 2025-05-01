package com.taskManager.taskManager.application.service;

import com.taskManager.taskManager.application.mapper.TaskApplicationMapper;
import com.taskManager.taskManager.domain.dto.TaskProjectionTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.taskManager.taskManager.domain.model.Comment;
import com.taskManager.taskManager.domain.model.Task;
import com.taskManager.taskManager.domain.model.Task.TaskStatus;
import com.taskManager.taskManager.domain.repository.CommentRepository;
import com.taskManager.taskManager.domain.repository.TaskRepository;
import com.taskManager.taskManager.domain.repository.UserRepository;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static com.taskManager.taskManager.common.security.SecurityUtils.getCurrentUsername;
import static com.taskManager.taskManager.domain.model.Task.TaskStatus.CREATED;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskApplicationMapper taskApplicationMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<Task> getTasks(String author, String assignee) {

        System.out.println("lists returned from repository:");
        List<TaskProjectionTO> repositoryTasks=taskRepository.findTasksFiltered(author, assignee);
        System.out.println(repositoryTasks);

        return taskApplicationMapper.mapTasks(repositoryTasks);
//        return taskRepository.findTasksFiltered(author, assignee).stream().map(taskProjectionTO -> Task.builder().id(taskProjectionTO.getId())
//                .title(taskProjectionTO.getTitle())
//                .description(taskProjectionTO.getDescription())
//                .status(taskProjectionTO.getStatus())
//                .assignee(taskProjectionTO.getAssignee())
//                .author(taskProjectionTO.getAuthor())
//                .totalComments(taskProjectionTO.getTotalComments().intValue())
//                .createdDate(taskProjectionTO.getCreatedDate())
//                .build()).toList();
    }

    @Transactional
    public Task addTask(String title, String description) {

        Task task = Task.builder()
                .title(title)
                .description(description)
                .status(CREATED)
                .author(getCurrentUsername())
                .assignee("none")
                .build();

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskAssignee(Long taskId, String assignee) {

        boolean isAssigneeValid = userRepository.existsByUsername(assignee) || "none".equalsIgnoreCase(assignee);

        if (!isAssigneeValid) {
            throw new ResponseStatusException(NOT_FOUND, "User with username '%s' was not found.".formatted(assignee));
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task with id '%s' was not found.".formatted(taskId)));

        String currentUser = getCurrentUsername();

        if (!currentUser.equalsIgnoreCase(task.getAuthor())) {
            throw new ResponseStatusException(FORBIDDEN, "User with username '%s' is attempting to assign a task, but it's not its author.".formatted(currentUser));
        }

        task.setAssignee(assignee);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTaskStatus(Long taskId, TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task with id '%s' was not found.".formatted(taskId)));

        String currentUser = getCurrentUsername();

        boolean isUserAuthorizedToEditTask = Set.of(task.getAuthor(), task.getAssignee()).stream()
                .anyMatch(currentUser::equalsIgnoreCase);

        if (!isUserAuthorizedToEditTask) {
            throw new ResponseStatusException(FORBIDDEN, "User with username '%s' is attempting to edit task, but is neither its author nor assignee.".formatted(currentUser));
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Transactional
    public void addComment(Long taskId, String text) {

        boolean isTaskPresent = taskRepository.existsById(taskId);

        if (!isTaskPresent) {
            throw new ResponseStatusException(NOT_FOUND, "Task with id '%s' was not found.".formatted(taskId));
        }

        Comment comment = Comment.builder()
                .taskId(taskId)
                .text(text)
                .author(getCurrentUsername())
                .build();

        commentRepository.save(comment);
    }

    public List<Comment> getTaskComments(Long taskId) {
        boolean isTaskPresent = taskRepository.existsById(taskId);

        if (!isTaskPresent) {
            throw new ResponseStatusException(NOT_FOUND, "Task with id '%s' was not found.".formatted(taskId));
        }

        return commentRepository.findAllByTaskId(taskId);
    }
}
