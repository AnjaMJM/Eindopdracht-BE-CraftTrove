package com.crafter.crafttroveapi.exceptions;

public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException() {
        super ("Authentication failed");
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
