package com.taskManager.taskManager.application.mapper;

import com.taskManager.taskManager.domain.dto.TaskProjectionTO;
import com.taskManager.taskManager.domain.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TaskApplicationMapper {

    Task map(TaskProjectionTO taskProjectionTO);

    List<Task> mapTasks(List<TaskProjectionTO> taskProjectionTOs);
}
