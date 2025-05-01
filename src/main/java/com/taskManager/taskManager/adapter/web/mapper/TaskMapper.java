package com.taskManager.taskManager.adapter.web.mapper;


import com.taskManager.taskManager.adapter.web.dto.CommentResponseTO;
import com.taskManager.taskManager.adapter.web.dto.TaskResponseTO;
import com.taskManager.taskManager.adapter.web.dto.TaskResponseTO.TaskStatusTE;
import com.taskManager.taskManager.domain.model.Comment;
import com.taskManager.taskManager.domain.model.Task;
import com.taskManager.taskManager.domain.model.Task.TaskStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseTO map(Task task);

    TaskStatusTE map(TaskStatus taskStatus);

    List<TaskResponseTO> mapTasks(List<Task> tasks);

    CommentResponseTO map(Comment comment);

    List<CommentResponseTO> mapComments(List<Comment> comments);
}
