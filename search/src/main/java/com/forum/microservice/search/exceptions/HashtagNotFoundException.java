package com.forum.microservice.search.exceptions;

public class HashtagNotFoundException extends RuntimeException {
    public HashtagNotFoundException(String message) {
        super(message);
    }

    public HashtagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashtagNotFoundException(Throwable cause) {
        super(cause);
    }
}
