package com.taskManager.taskManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import com.taskManager.taskManager.domain.dto.TaskProjectionTO;
import com.taskManager.taskManager.domain.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
    SELECT task.id AS id,
           task.title AS title,
           task.description AS description,
           task.status AS status,
           task.author AS author,
           task.assignee AS assignee,
           task.createdDate AS createdDate,
           COUNT(comment.id) AS totalComments
    FROM Task task
    LEFT JOIN Comment comment ON comment.taskId = task.id
    WHERE (:author IS NULL OR task.author = :author)
      AND (:assignee IS NULL OR task.assignee = :assignee)
    GROUP BY task.id, task.title, task.description, task.status, task.author, task.assignee, task.createdDate
    ORDER BY task.createdDate DESC
    """)
    List<TaskProjectionTO> findTasksFiltered(@Param("author") String author, @Param("assignee") String assignee);

    boolean existsById(@NonNull Long id);
}
