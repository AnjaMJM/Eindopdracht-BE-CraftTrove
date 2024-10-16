package com.crafter.crafttroveapi.exceptions;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException() {
        super("A record with the same title already exists.");
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
