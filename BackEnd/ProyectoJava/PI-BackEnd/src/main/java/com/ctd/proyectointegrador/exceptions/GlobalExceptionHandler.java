package com.ctd.proyectointegrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>("Bad Request. Please check your data and try again.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>("Specified id or resource does not exists.", HttpStatus.NOT_FOUND);
    }

}
