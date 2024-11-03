package com.crafter.crafttroveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictWithResourceStateException extends RuntimeException{

    public ConflictWithResourceStateException() {
        super("A record with the same title already exists.");
    }

    public ConflictWithResourceStateException(String message) {
        super(message);
    }
}
