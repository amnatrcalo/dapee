package com.forum.microservice.postcomment.exceptions;

public class PostNotFoundException extends RuntimeException {
  public PostNotFoundException(String message) {
    super(message);
  }

  public PostNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public PostNotFoundException(Throwable cause) {
    super(cause);
  }
}
