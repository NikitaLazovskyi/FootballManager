package com.fmanager.exception.handlers;

import com.fmanager.dto.Response;
import com.fmanager.exception.EntityNotFoundException;
import com.fmanager.exception.InvalidEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {//унаследовались от обработчика-заготовки

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleException(EntityNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<Response> handleException(InvalidEntityException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}