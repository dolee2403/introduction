package com.sparta.task.service;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.repository.TasksRepository;
import com.sparta.task.exception.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;

    // 새로운 Task를 생성하는 메서드
    public Tasks createTasks(TasksRequestDTO dto, String username) {
        var newTasks = dto.toEntity();
        newTasks.setManager(username);
        return tasksRepository.save(newTasks);
    }

    // 특정 ID의 Task를 조회하는 메서드
    public Tasks getTasks(Long tasksId) {
        return tasksRepository.findById(tasksId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 모든 Task를 조회하는 메서드
    public List<Tasks> getTask() {
        return tasksRepository.findAll(Sort.by("createdAt").descending());
    }

    // 특정 ID의 Task를 업데이트하는 메서드
    public Tasks updateTasks(Long tasksId, TasksRequestDTO dto, String username) {
        Tasks tasks = checkPWAndGetTasks(tasksId, dto.getPassword());

        // Task 작성자가 아닌 경우 예외 발생
        if (!tasks.getManager().equals(username)) {
            throw new GlobalExceptionHandler.UserNotAuthorizedException();
        }

        // Task의 제목과 내용을 업데이트
        tasks.setTitle(dto.getTitle());
        tasks.setContent(dto.getContent());
        return tasksRepository.save(tasks);
    }

    // 비밀번호를 확인하고 Task를 조회하는 메서드
    private Tasks checkPWAndGetTasks(Long tasksId, String password) {
        Tasks tasks = getTasks(tasksId);

        // 비밀번호가 일치하지 않는 경우 예외 발생
        if (tasks.getPassword() != null && !Objects.equals(tasks.getPassword(), password)) {
            throw new IllegalArgumentException();
        }
        return tasks;
    }

    // 특정 ID의 Task를 삭제하는 메서드
    public void deleteTasks(Long tasksId, String password, String username) {
        Tasks tasks = checkPWAndGetTasks(tasksId, password);

        // Task 작성자가 아닌 경우 예외 발생
        if (!tasks.getManager().equals(username)) {
            throw new GlobalExceptionHandler.UserNotAuthorizedException();
        }

        // Task 삭제
        tasksRepository.delete(tasks);
    }
}
