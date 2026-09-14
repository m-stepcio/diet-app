package com.diet.app.exceptions;

public class BadUnitException extends RuntimeException{
    public BadUnitException(String inputUnit, String outputUnit) {
        super("Could not convert " + inputUnit + " into " + outputUnit);
    }
}
