package com.example.demo.endpoints.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        log.warn("Handle RuntimeException", ex);
        var httpStatus = BAD_REQUEST;
        var errorResponse = ErrorResponse.of(ex.getMessage(), httpStatus.value(), httpStatus.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnException(final Exception ex, final WebRequest request) {
        log.error("Handle Exception", ex);
        final HttpStatus httpStatus = INTERNAL_SERVER_ERROR;
        var errorResponse = ErrorResponse.of(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

}
