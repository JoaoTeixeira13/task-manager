package com.taskManager.taskManager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskManager.taskManager.domain.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTaskId(Long taskId);
}
