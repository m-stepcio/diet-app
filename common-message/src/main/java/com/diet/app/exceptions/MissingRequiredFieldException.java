package com.diet.app.exceptions;

public class MissingRequiredFieldException extends RuntimeException{

    public MissingRequiredFieldException(String field) {
        super("Missing required field: " + field);
    }
}
