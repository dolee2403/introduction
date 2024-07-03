package com.sparta.task.service;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.repository.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor  // 생성자 주입을 위한 Lombok
public class TasksService {
    private final TasksRepository tasksRepository;  // Tasks 엔티티의 CRUD 작업을 수행하는 Repository

    // 할 일 생성
    public Tasks createTasks(TasksRequestDTO dto) {
        // DTO 객체를 엔티티로 변환하여 저장
        var newTasks = dto.toEntity();
        return tasksRepository.save(newTasks);
    }

    // 할 일 단건 조회
    public Tasks getTasks(Long tasksId) {
        // 주어진 tasksId로 엔티티를 조회하고, 없으면 예외를 발생
        return tasksRepository.findById(tasksId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할 일 전체 조회
    public List<Tasks> getTask() {
        // 모든 할 일을 생성 일시 기준 내림차순으로 조회하여 반환
        return tasksRepository.findAll(Sort.by("createAt").descending());
    }

    // 할 일 수정
    public Tasks updateTasks(Long tasksId, TasksRequestDTO dto) {
        // 비밀번호 검증 후 엔티티를 업데이트하고 저장
        Tasks tasks = checkPWAndGetTasks(tasksId, dto.getPassword());

        tasks.setTitle(dto.getTitle());
        tasks.setContent(dto.getContent());
        tasks.setManager(dto.getManager());

        return tasksRepository.save(tasks);
    }

    // 비밀번호 검증
    private Tasks checkPWAndGetTasks(Long tasksId, String password) {
        // 주어진 tasksId로 엔티티를 조회하고, 비밀번호를 검증하여 일치하지 않으면 예외를 발생
        Tasks tasks = getTasks(tasksId);

        if (tasks.getPassword() != null && !Objects.equals(tasks.getPassword(), password)) {
            throw new IllegalArgumentException();
        }
        return tasks;
    }

    // 할 일 삭제 메서드
    public void deleteTasks(Long tasksId, String password) {
        // 비밀번호 검증 후 엔티티를 삭제
        Tasks tasks = checkPWAndGetTasks(tasksId, password);
        tasksRepository.delete(tasks);
    }
}
