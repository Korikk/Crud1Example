package com.Crud1.Crud1Example.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> illegalException(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"1000",exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerNotFound.class)
    public final ResponseEntity<ExceptionResponse> customerNotFound(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"2000",exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> exception(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"3000",exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }



}
