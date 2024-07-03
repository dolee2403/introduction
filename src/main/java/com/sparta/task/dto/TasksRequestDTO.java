package com.sparta.task.dto;

import com.sparta.task.entity.Tasks;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasksRequestDTO {

    // Task의 제목을 저장하는 필드
    private String title;

    // Task의 내용을 저장하는 필드
    private String content;

    // Task를 관리하는 담당자를 저장하는 필드
    private String manager;

    // Task의 비밀번호를 저장하는 필드
    private String password;

    // TasksRequestDTO 객체를 Tasks 엔티티로 변환하는 메서드
    public Tasks toEntity() {
        return Tasks.builder()
                .title(title)
                .content(content)
                .manager(manager)
                .password(password)
                .build();
    }
}
