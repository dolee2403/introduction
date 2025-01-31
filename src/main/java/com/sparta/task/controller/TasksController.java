package com.sparta.task.controller;

import com.sparta.task.dto.TasksRequestDTO;
import com.sparta.task.dto.TasksResponseDTO;
import com.sparta.task.entity.Tasks;
import com.sparta.task.exception.GlobalExceptionHandler;
import com.sparta.task.service.TasksService;
import com.sparta.task.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

// '/api/tasks' URL로 요청을 처리하는 컨트롤러 클래스
@RequestMapping("/api/tasks")
@RestController
@AllArgsConstructor
public class TasksController {

    private final TasksService tasksService;
    private final JwtUtil jwtUtil;

    // 새로운 Task를 생성하는 메서드 (POST 요청 처리)
    @PostMapping
    public ResponseEntity<TasksResponseDTO> postTasks(@RequestBody TasksRequestDTO dto, HttpServletRequest request) {
        // JWT 토큰을 헤더에서 가져와 유효성 검증
        String token = jwtUtil.getJwtFromHeader(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new GlobalExceptionHandler.TokenInvalidException();
        }

        // 토큰에서 사용자 정보 추출
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();

        // 클라이언트에서 받은 데이터를 이용해 새로운 Task 생성
        Tasks tasks = tasksService.createTasks(dto, username);
        // 생성된 Task를 응답 DTO로 변환
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        // HTTP 200 OK 응답과 함께 생성된 Task를 클라이언트에게 반환
        return ResponseEntity.ok().body(response);
    }

    // 특정 ID의 Task를 조회하는 메서드 (GET 요청 처리)
    @GetMapping("{tasksId}")
    public ResponseEntity<TasksResponseDTO> getTasks(@PathVariable Long tasksId) {
        // 클라이언트에서 받은 ID를 이용해 Task 조회
        Tasks tasks = tasksService.getTasks(tasksId);
        // 조회된 Task를 응답 DTO로 변환
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        // HTTP 200 OK 응답과 함께 조회된 Task를 클라이언트에게 반환
        return ResponseEntity.ok().body(response);
    }

    // 모든 Task를 조회하는 메서드 (GET 요청 처리)
    @GetMapping
    public ResponseEntity<List<TasksResponseDTO>> getTask() {
        // 모든 Task를 조회
        List<Tasks> tasks = tasksService.getTask();
        // 조회된 Task 리스트를 응답 DTO 리스트로 변환
        List<TasksResponseDTO> response = tasks.stream()
                .map(TasksResponseDTO::new)
                .collect(Collectors.toList());
        // HTTP 200 OK 응답과 함께 모든 Task를 클라이언트에게 반환
        return ResponseEntity.ok().body(response);
    }

    // 특정 ID의 Task를 업데이트하는 메서드 (PUT 요청 처리)
    @PutMapping("/{tasksId}")
    public ResponseEntity<TasksResponseDTO> putTasks(@PathVariable Long tasksId, @RequestBody TasksRequestDTO dto, HttpServletRequest request) {
        // JWT 토큰을 헤더에서 가져와 유효성 검증
        String token = jwtUtil.getJwtFromHeader(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new GlobalExceptionHandler.TokenInvalidException();
        }

        // 토큰에서 사용자 정보 추출
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();

        // 클라이언트에서 받은 ID와 데이터를 이용해 Task 업데이트
        Tasks tasks = tasksService.updateTasks(tasksId, dto, username);
        // 업데이트된 Task를 응답 DTO로 변환
        TasksResponseDTO response = new TasksResponseDTO(tasks);
        // HTTP 200 OK 응답과 함께 업데이트된 Task를 클라이언트에게 반환
        return ResponseEntity.ok().body(response);
    }

    // 특정 ID의 Task를 삭제하는 메서드 (DELETE 요청 처리)
    @DeleteMapping("/{tasksId}")
    public ResponseEntity<Void> deleteTasks(@PathVariable Long tasksId, @RequestBody TasksRequestDTO dto, HttpServletRequest request) {
        // JWT 토큰을 헤더에서 가져와 유효성 검증
        String token = jwtUtil.getJwtFromHeader(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new GlobalExceptionHandler.TokenInvalidException();
        }

        // 토큰에서 사용자 정보 추출
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();

        // 클라이언트에서 받은 ID와 데이터를 이용해 Task 삭제
        tasksService.deleteTasks(tasksId, dto.getPassword(), username);
        // HTTP 200 OK 응답을 반환 (본문은 없음)
        return ResponseEntity.ok().build();
    }
}
