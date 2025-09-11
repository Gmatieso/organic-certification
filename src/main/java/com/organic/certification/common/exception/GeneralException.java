package com.organic.certification.common.exception;

public class GeneralException extends Exception {
    public GeneralException(String message) {
        super(message);
    }

    public GeneralException() {
        super("An error occurred while processing  your request. Please try again later.");
    }
}
