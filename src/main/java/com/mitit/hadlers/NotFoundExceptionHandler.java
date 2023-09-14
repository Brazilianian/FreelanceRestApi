package com.mitit.hadlers;

import com.mitit.dto.ExceptionResponseDto;
import com.mitit.exception.notfound.AbstractNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> catchAbstractNotFoundException(AbstractNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
