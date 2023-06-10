package com.forum.microservice.login.error.exception;


import com.forum.microservice.login.error.ErrorConstants;

public class WrappedException extends RuntimeException{

    private ErrorConstants errorConstant;

    public WrappedException(ErrorConstants errorConstant) {
        super(errorConstant.getMessage());
        this.errorConstant = errorConstant;
    }

    public ErrorConstants getErrorConstant() {
        return errorConstant;
    }

    public void setErrorConstant(ErrorConstants errorConstant) {
        this.errorConstant = errorConstant;
    }
}
