package com.taskManager.taskManager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String author;
    private String assignee;

    @Transient
    private Integer totalComments;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdDate;

    public enum TaskStatus {
        COMPLETED,
        CREATED,
        IN_PROGRESS
    }

}
