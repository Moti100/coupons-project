package com.moti.server.exception;


import com.moti.server.enums.ErrorType;

public class ApplicationException extends Exception{
    private ErrorType errorType;

    public ApplicationException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ApplicationException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ApplicationException(String message, Exception e, ErrorType errorType) {
        super(message,e);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "errorType=" + errorType +
                '}';
    }
}
