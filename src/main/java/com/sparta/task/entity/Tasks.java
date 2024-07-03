package com.sparta.task.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// JPA 엔티티로 지정
@Entity
@Getter
@NoArgsConstructor  // 기본 생성자를 자동으로 생성하는 Lombok 애노테이션
public class Tasks {
    @Id  // 데이터베이스의 주키(primary key)를 나타내는 필드
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  // 데이터베이스 시퀀스를 사용하여 자동으로 ID를 생성
    @Column(name = "tasks_id", nullable = false)  // 데이터베이스 테이블의 컬럼 이름을 지정하고, null 값을 허용하지 않음
    private Long tasksId;  // 작업의 고유 식별자

    private String title;  // 작업의 제목

    private String content;  // 작업의 내용

    private String manager;  // 작업을 담당하는 담당자의 이름

    private String password;  // 보안을 위한 비밀번호 필드

    private LocalDateTime createdAt;  // 작업이 생성된 시간

    // 빌더 패턴을 사용하여 객체를 생성할 수 있는 생성자
    @Builder
    public Tasks(String title, String content, String manager, String password) {
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.password = password;
        this.createdAt = LocalDateTime.now();  // 객체가 생성될 때 현재 시간으로 생성 일시를 설정
    }

    // Setter 메서드들
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
