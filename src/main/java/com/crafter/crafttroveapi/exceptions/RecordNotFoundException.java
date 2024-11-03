package com.crafter.crafttroveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
        super("There is no item matching your request");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
