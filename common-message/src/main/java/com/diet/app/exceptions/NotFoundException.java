package com.diet.app.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id){
        super("Could not find element with id {}".formatted(id));
    }

    public NotFoundException(String message){
        super(message);
    }
}
