package com.epam.emotionalhelp.exceptionhandler;

import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
       return ResponseEntity.badRequest().body(ResponseMessage.RESOURCE_NOT_FOUND);
    }
}
