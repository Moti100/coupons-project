package com.moti.server.exceptionhandler;

import com.moti.server.dto.ServerErrorData;
import com.moti.server.exception.ApplicationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ServerErrorData exceptionCustomerResponse(HttpServletResponse httpServletResponse, Exception exception) {
        System.out.println(exception);
        if (exception instanceof ApplicationException) {
            int errorCode = ((ApplicationException) exception).getErrorType().getInternalErrorCode();
            String errorMessage = ((ApplicationException) exception).getErrorType().getErrorMessage();
            String errorType = String.valueOf(((ApplicationException) exception).getErrorType());

            httpServletResponse.setStatus(errorCode);
            if (((ApplicationException) exception).getErrorType().isShowStackTrace()) {
                exception.printStackTrace();
            }
            return new ServerErrorData(errorCode, errorMessage, errorType);
        }

//Returning general error
        httpServletResponse.setStatus(600);
        return new ServerErrorData(600, "Something went wrong.Please try later", "GENERAL ERROR");
    }
}
