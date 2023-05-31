package com.forum.microservice.notification.exceptions;

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
