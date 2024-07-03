package com.sparta.task.controller;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.dto.TasksResponseDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.service.TasksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TasksController {

    public final TasksService tasksService;

    @PostMapping("/v1.0/tasks")
    public ResponseEntity postTasks(@RequestBody TasksRequestDTO dto) {
        Tasks tasks = tasksService.createTasks(dto);
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        return ResponseEntity.ok().body(response);
    }
}
