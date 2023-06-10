package com.forum.springcloud.gateway.error.exception;


import com.forum.springcloud.gateway.error.ErrorConstants;

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
