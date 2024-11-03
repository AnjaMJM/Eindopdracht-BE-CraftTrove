package com.crafter.crafttroveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequirementsNotMetException extends RuntimeException {

    public RequirementsNotMetException() {
        super("Input does not meet the requirements.");
    }

    public RequirementsNotMetException(String message) {
        super(message);
    }
}
