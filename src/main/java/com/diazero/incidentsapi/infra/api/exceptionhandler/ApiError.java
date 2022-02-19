package com.diazero.incidentsapi.infra.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ObjectError> subErrors;


    ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
       this();
       this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = ex.getMessage();
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, Throwable ex, String message){
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public List<ObjectError> getSubErrors() {
        return subErrors;
    }

    private void addSubError(ObjectError error) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }

        subErrors.add(error);
    }

    public void addValidationErrors(List<ObjectError> allErrors) {
        allErrors.forEach(this::addSubError);
    }
}
