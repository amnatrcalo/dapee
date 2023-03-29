package com.forum.microservice.administration.exceptions;

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
