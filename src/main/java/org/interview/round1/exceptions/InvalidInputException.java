package org.interview.round1.exceptions;

public class InvalidInputException extends RouterException {

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidInputException(ErrorCodes errorCode) {
        super(errorCode.getMsg(), errorCode.getCode());
    }
}
