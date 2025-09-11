package com.organic.certification.common.exception;

import com.organic.certification.common.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    Boolean isStackTraceOn;

    public GlobalExceptionHandler(Environment env) {
        this.isStackTraceOn = Boolean.parseBoolean(env.getProperty("certification.init.enabled", "false"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus status) {
            var message =  exception.getMessage() != null ?  exception.getMessage() : "An unexpected error occurred.";
            if(status == HttpStatus.INTERNAL_SERVER_ERROR) {
                logger.error("Server error [{}]: {}", status.value(), message, exception);
                if(!(exception instanceof GeneralException)){
                    message = "An unexpected error occurred. Please try again.";
                }
            }
        ErrorResponse errorResponse = isStackTraceOn  ?  new ErrorResponse(status.value(), message, getStackTraceAsString(exception)) : new ErrorResponse(status.value(), message) ;
        return  ResponseEntity.status(status).body(errorResponse);

    }

    private String getStackTraceAsString(Exception exception) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : exception.getStackTrace()) {
            sb.append(element).append("\n");
        }
        return sb.toString();
    }
}
