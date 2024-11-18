package com.spring.repo.basemongo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class AppError {

  private HttpStatus status;

  private LocalDateTime timestamp;

  private String message;

  private String debugMessage;


  private AppError() {

    timestamp = LocalDateTime.now();

  }



  AppError(HttpStatus status) {

    this.status = status;

  }



  AppError(HttpStatus status, Throwable ex) {

    this.status = status;

    this.message = "Unexpected error";

    this.debugMessage = ex.getLocalizedMessage();

  }



  AppError(HttpStatus status, String message, Throwable ex) {

    this.status = status;

    this.message = message;

    this.debugMessage = ex.getLocalizedMessage();

  }

}
