package com.epam.emotionalhelp.controller.global;

import com.epam.emotionalhelp.exception.NoAccessException;
import com.epam.emotionalhelp.exception.NotAuthorizedException;
import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    private static final String NOT_AUTHORIZED = "You are not authorized";
    private static final String NO_ACCESS = "You have no access";
    private static final String SERVER_ERROR = "Server error";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFound(ResourceNotFoundException e) {
        return e.getType().name();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentsNotValid(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolation(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("; "));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public String notAuthorized() {
        return NOT_AUTHORIZED;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoAccessException.class)
    public String noAccess() {
        return NO_ACCESS;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        log.error(e.getMessage(), e);
        return SERVER_ERROR;
    }
}
