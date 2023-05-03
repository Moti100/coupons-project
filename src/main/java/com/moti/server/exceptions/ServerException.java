package com.moti.server.exceptions;

import com.moti.server.enums.ErrorType;
public class ServerException extends Exception{

    private ErrorType errorType;
    private int errorNumber;

    public ServerException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ServerException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ServerException(Exception e, ErrorType errorType, String message) {
        super(message, e);
        this.errorType = errorType;
    }

    //Getting ErrorType from object
    public ErrorType getErrorType() {
        return errorType;
    }

    public int getErrorNumber() {
        return errorNumber;
    }
}
