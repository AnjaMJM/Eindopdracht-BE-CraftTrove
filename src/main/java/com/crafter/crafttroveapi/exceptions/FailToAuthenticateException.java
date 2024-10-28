package com.crafter.crafttroveapi.exceptions;

public class FailToAuthenticateException extends RuntimeException {

    public FailToAuthenticateException() {
        super ("Authentication failed");
    }

    public FailToAuthenticateException(String message) {
        super(message);
    }
}

