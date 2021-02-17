package com.swedbank.academy.demoserver.controller;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionController {
    @ExceptionHandler(value = PersonNotFoundException.class) // apsirasom kaip handlinsim
    public ResponseEntity<Object> exception(PersonNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}