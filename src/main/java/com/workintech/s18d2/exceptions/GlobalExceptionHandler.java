package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(PlantException exception){
        log.error("entity exception: ", exception);
        EntityErrorResponse response = new EntityErrorResponse(exception.getHttpStatus().value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(Exception exception){
        log.error("exception: ", exception);
        EntityErrorResponse response = new EntityErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
