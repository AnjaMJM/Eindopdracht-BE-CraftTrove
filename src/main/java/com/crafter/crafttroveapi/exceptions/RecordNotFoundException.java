package com.crafter.crafttroveapi.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
        super("There is no item matching your request");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
