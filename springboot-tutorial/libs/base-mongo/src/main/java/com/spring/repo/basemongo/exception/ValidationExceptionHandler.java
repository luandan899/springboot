package com.spring.repo.basemongo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<String> errors =
        ex.getBindingResult().getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());
    return ResponseEntity.status(BAD_REQUEST)
        .body(
            Map.of(
                "status",
                BAD_REQUEST.value(),
                "error",
                BAD_REQUEST.getReasonPhrase(),
                "message",
                errors,
                "path",
                request.getRequestURI()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(
      HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(
                        Map.of(
                                "status",
                                BAD_REQUEST.value(),
                                "error",
                                BAD_REQUEST.getReasonPhrase(),
                                "message",
                                ex.getMessage(),
                                "path",
                                request.getRequestURI()));
    }

}
