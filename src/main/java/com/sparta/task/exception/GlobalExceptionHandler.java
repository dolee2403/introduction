package com.sparta.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 처리하기 위한 어드바이스 클래스
@RestController
public class GlobalExceptionHandler {

    // 토큰 유효하지 않을 때 발생하는 예외 처리
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<String> handleTokenInvalidException(TokenInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰이 유효하지 않습니다.");
    }

    // 작성자가 아닌 경우 발생하는 예외 처리
    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<String> handleUserNotAuthorizedException(UserNotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작성자만 삭제/수정할 수 있습니다.");
    }

    // 중복된 username으로 회원가입 시 발생하는 예외 처리
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 `username` 입니다.");
    }

    // 로그인 시 회원 정보를 찾을 수 없을 때 발생하는 예외 처리
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원을 찾을 수 없습니다.");
    }

    // Custom Exception Classes 정의
    public static class TokenInvalidException extends RuntimeException {
    }

    public static class UserNotAuthorizedException extends RuntimeException {
    }

    public static class UsernameAlreadyExistsException extends RuntimeException {
    }

    public static class UserNotFoundException extends RuntimeException {
    }
}
