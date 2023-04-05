package com.forum.microservice.search.exceptions;

public class SubforumNotFoundException extends RuntimeException {
    public SubforumNotFoundException(String message) {
        super(message);
    }

    public SubforumNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubforumNotFoundException(Throwable cause) {
        super(cause);
    }
}
