package com.forum.microservice.administration.exceptions;

public class CommentNotFoundException extends RuntimeException {
  public CommentNotFoundException(String message) {
    super(message);
  }

  public CommentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommentNotFoundException(Throwable cause) {
    super(cause);
  }
}
