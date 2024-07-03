package com.sparta.task.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tasks_id", nullable = false)
    private Long tasksId;

    private String title;

    private String content;

    private String manager;

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public Tasks(String title, String content, String manager, String password) {
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
