package com.forum.microservice.administration.exceptions;

public class ReportNotFoundException extends RuntimeException {
  public ReportNotFoundException(String message) {
    super(message);
  }

  public ReportNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReportNotFoundException(Throwable cause) {
    super(cause);
  }
}
