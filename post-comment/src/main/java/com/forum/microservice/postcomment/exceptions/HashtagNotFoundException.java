package com.forum.microservice.postcomment.exceptions;

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
