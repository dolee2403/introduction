package com.sparta.task.dto;

import com.sparta.task.entity.Tasks;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasksRequestDTO {
    private String title;

    private String content;

    private String manager;

    private String password;

    public Tasks toEntity() {
        return Tasks.builder()
                .title(title)
                .content(content)
                .manager(manager)
                .password(password)
                .build();
    }
}
