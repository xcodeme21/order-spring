package com.example.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseHelper responseHelper;

    public GlobalExceptionHandler(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<String>> handleValidation(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(responseHelper.error(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> handleResponseStatus(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(responseHelper.error(e.getReason()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<WebResponse<String>> handleNotFound(NoHandlerFoundException ex) {
        return new ResponseEntity<>(responseHelper.error("Endpoint not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<WebResponse<String>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(responseHelper.error("Method not allowed"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<WebResponse<String>> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<>(responseHelper.error("Unsupported media type"), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<String>> handleGeneric(Exception ex) {
        return new ResponseEntity<>(responseHelper.error("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
