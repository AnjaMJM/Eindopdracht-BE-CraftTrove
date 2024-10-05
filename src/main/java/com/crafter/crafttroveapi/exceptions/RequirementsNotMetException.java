package com.crafter.crafttroveapi.exceptions;

public class RequirementsNotMetException extends RuntimeException {

    public RequirementsNotMetException() {
        super("Input does not meet the requirements.");
    }

    public RequirementsNotMetException(String message) {
        super(message);
    }
}
