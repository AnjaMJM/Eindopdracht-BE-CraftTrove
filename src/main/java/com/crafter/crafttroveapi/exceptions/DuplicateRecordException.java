package com.crafter.crafttroveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException() {
        super("A record with the same title already exists.");
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
