package com.sparta.task.dto;

import com.sparta.task.entity.Tasks;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TasksResponseDTO {

    private Long tasksId;

    private String title;

    private String content;

    private String manager;

    private LocalDateTime createAt;

    public TasksResponseDTO(Tasks tasks) {
        this.tasksId = tasks.getTasksId();
        this.title = tasks.getTitle();
        this.content = tasks.getContent();
        this.manager = tasks.getManager();
    }
}
