package com.epam.emotionalhelp.controller.global;

import com.epam.emotionalhelp.exception.NoAccessException;
import com.epam.emotionalhelp.exception.NotAuthorizedException;
import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.rmi.AccessException;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> resourceNotFound(ResourceNotFoundException e) {
        String message = e.getMessage() == null
                ? e.getClass().getSimpleName()
                : e.getMessage();
        return Map.of("code", "404",
                "message", message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentsNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + " : " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return Map.of("code", "400",
                "message", message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> constraintViolation(ConstraintViolationException e) {
        String message = e.getMessage() == null
                ? e.getClass().getSimpleName()
                : e.getMessage();
        return Map.of("code", "400",
                "message", message);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public Map<String, String> notAuthorized() {
        return Map.of("code", "401",
                "message", "You are not authorized");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoAccessException.class)
    public Map<String, String> noAccess() {
        return Map.of("code", "403",
                "message", "You have no access");
    }
}
