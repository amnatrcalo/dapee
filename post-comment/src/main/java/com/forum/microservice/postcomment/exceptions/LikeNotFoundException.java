package com.forum.microservice.postcomment.exceptions;

public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(String message) {
        super(message);
    }

    public LikeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeNotFoundException(Throwable cause) {
        super(cause);
    }
}
