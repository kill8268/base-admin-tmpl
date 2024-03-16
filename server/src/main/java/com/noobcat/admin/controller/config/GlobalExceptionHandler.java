package com.noobcat.admin.controller.config;

import org.openapitools.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleAllExceptions(Exception ex) {
    return ResponseEntity.internalServerError().body(new Error().message("服务器异常"));
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Error> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new Error().message(ex.getMessage()));
  }
}
