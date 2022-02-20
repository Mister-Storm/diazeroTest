package com.diazero.incidentsapi.infra.api.exceptionhandler;

import com.diazero.incidentsapi.domain.usecases.IncidentNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= IncidentNotFoundException.class)
    protected ResponseEntity<ApiError> handleIncidentNotFoundException( RuntimeException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(status, ex);
        error.addValidationErrors(ex.getAllErrors());


        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
