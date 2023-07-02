package com.Crud1.Crud1Example.advice;

public class CustomerNotFound extends RuntimeException{

    public CustomerNotFound(String message) {
        super(message);
    }
}
