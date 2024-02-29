package com.noobcat.admin.controller;

import org.openapitools.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorModel> handleAllExceptions(Exception ex) {
    return ResponseEntity.internalServerError().body(new ErrorModel().message("服务器异常"));
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorModel> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new ErrorModel().message(ex.getMessage()));
  }
}
