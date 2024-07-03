package com.sparta.task.controller;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.dto.TasksResponseDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.service.TasksService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1.0/tasks")
@RestController
@AllArgsConstructor
public class TasksController {

    public final TasksService tasksService;

    @PostMapping
    public ResponseEntity postTasks(@RequestBody TasksRequestDTO dto) {
        Tasks tasks = tasksService.createTasks(dto);
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{tasksId}")
    public ResponseEntity<TasksResponseDTO> getTasks(@PathVariable Long tasksId) {
        Tasks tasks = tasksService.getTasks(tasksId);
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<TasksResponseDTO>> getTask() {
        List<Tasks> tasks = tasksService.getTask();
        List<TasksResponseDTO> response = tasks.stream()
                .map(TasksResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}
