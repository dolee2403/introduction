package com.sparta.task.dto;

// 필요한 클래스 import
import com.sparta.task.entity.Tasks;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// Lombok 애노테이션: Getter와 Setter 메서드를 자동 생성
@Getter
@Setter
public class TasksResponseDTO {

    // 작업의 고유 식별자
    private Long tasksId;

    // 작업 제목
    private String title;

    // 작업 내용
    private String content;

    // 담당자 이름
    private String manager;

    // 작업 생성 일시
    private LocalDateTime createdAt;

    // Tasks 엔티티를 기반으로 DTO 객체 생성
    public TasksResponseDTO(Tasks tasks) {
        this.tasksId = tasks.getTasksId();
        this.title = tasks.getTitle();
        this.content = tasks.getContent();
        this.manager = tasks.getManager();
        this.createdAt = tasks.getCreatedAt();
    }
}
