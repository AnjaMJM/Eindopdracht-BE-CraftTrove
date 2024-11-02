package com.crafter.crafttroveapi.exceptions;

public class ConflictWithResourceStateException extends RuntimeException{

    public ConflictWithResourceStateException() {
        super("A record with the same title already exists.");
    }

    public ConflictWithResourceStateException(String message) {
        super(message);
    }
}
