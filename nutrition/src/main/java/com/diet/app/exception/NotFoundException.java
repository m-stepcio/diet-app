package com.diet.app.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id){
        super("Could not find element with id {}".formatted(id));
    }
}
