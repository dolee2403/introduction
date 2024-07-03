package com.sparta.task.service;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.repository.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;

    // 할 일 생성
    public Tasks createTasks(TasksRequestDTO dto) {
        var newTasks = dto.toEntity();
        return tasksRepository.save(newTasks);
    }

    // 할 일 단건 조회
    public Tasks getTasks(Long tasksId) {
        return tasksRepository.findById(tasksId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
